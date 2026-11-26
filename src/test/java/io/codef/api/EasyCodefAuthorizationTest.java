package io.codef.api;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class EasyCodefAuthorizationTest {

	@Test
	public void sendsAuthorizationWithUtf8DefaultCharset() throws Exception {
		runProbe("UTF-8");
	}

	@Test
	public void sendsAuthorizationWithLatin1DefaultCharset() throws Exception {
		runProbe("ISO-8859-1");
	}

	private void runProbe(String charset) throws Exception {
		String java = new File(System.getProperty("java.home"), "bin/java").getPath();
		String classpath = System.getProperty("surefire.test.class.path", System.getProperty("java.class.path"));
		Process process = new ProcessBuilder(java, "-Dfile.encoding=" + charset, "-cp", classpath,
				HeaderProbe.class.getName()).redirectErrorStream(true).start();
		try {
			assertTrue("Header probe timed out", process.waitFor(30, TimeUnit.SECONDS));
			try (Scanner output = new Scanner(process.getInputStream(), "UTF-8").useDelimiter("\\A")) {
				String message = output.hasNext() ? output.next() : "";
				assertEquals(message, 0, process.exitValue());
			}
		} finally {
			process.destroyForcibly();
		}
	}

	// Isolate the JVM-global URL handler so no other tests or real network calls are affected.
	public static class HeaderProbe {
		private static CapturedConnection connection;

		public static void main(String[] args) throws Exception {
			URL.setURLStreamHandlerFactory(protocol -> new URLStreamHandler() {
				@Override
				protected URLConnection openConnection(URL url) {
					assertEquals(EasyCodefConstant.OAUTH_DOMAIN + EasyCodefConstant.GET_TOKEN, url.toString());
					connection = new CapturedConnection(url);
					return connection;
				}
			});
			check("", "", "Og==");
			check("a", "", "YTo=");
			check("ab", "", "YWI6");
			check("Aladdin", "open sesame", "QWxhZGRpbjpvcGVuIHNlc2FtZQ==");
			check(null, null, "bnVsbDpudWxs");
			check("caf\u00e9", "\u20ac\u97d3\ud83d\ude00", null);
			char[] longId = new char[1000];
			Arrays.fill(longId, 'x');
			check(new String(longId), "secret", null);
		}

		private static void check(String id, String secret, String expectedBase64) {
			assertNotNull(EasyCodefConnector.publishToken(id, secret));
			String header = connection.getRequestProperty("Authorization");
			assertTrue(header.startsWith("Basic "));
			String encoded = header.substring(6);
			if (expectedBase64 != null) {
				assertEquals(expectedBase64, encoded);
			}
			assertArrayEquals((id + ":" + secret).getBytes(), Base64.getDecoder().decode(encoded));
			assertFalse(encoded.contains("\r"));
			assertFalse(encoded.contains("\n"));
			assertEquals("POST", connection.getRequestMethod());
			assertEquals("application/x-www-form-urlencoded", connection.getRequestProperty("Content-Type"));
			assertArrayEquals("grant_type=client_credentials&scope=read".getBytes(), connection.body.toByteArray());
		}
	}

	private static class CapturedConnection extends HttpURLConnection {
		private final ByteArrayOutputStream body = new ByteArrayOutputStream();

		CapturedConnection(URL url) {
			super(url);
		}

		@Override
		public OutputStream getOutputStream() {
			return body;
		}

		@Override
		public int getResponseCode() {
			return HTTP_OK;
		}

		@Override
		public InputStream getInputStream() {
			return new ByteArrayInputStream("{\"access_token\":\"test-token\"}".getBytes(StandardCharsets.UTF_8));
		}

		@Override
		public void connect() { }

		@Override
		public void disconnect() { }

		@Override
		public boolean usingProxy() {
			return false;
		}
	}
}
