package pl.edu.agh.kis.async_tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ImageFetcher {
    public static Bitmap fetchAvatar(String email) {
        String url = "https://www.gravatar.com/avatar/" + md5(email.toLowerCase().trim());
        Log.e("asfd", md5(email.toLowerCase().trim()));
        Bitmap avatar = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            avatar = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return avatar;
    }

    private static String md5(String s)
    {
        MessageDigest digest;
        try
        {
            digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes(Charset.forName("US-ASCII")),0,s.length());
            byte[] magnitude = digest.digest();
            BigInteger bi = new BigInteger(1, magnitude);
            return String.format("%0" + (magnitude.length << 1) + "x", bi);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
