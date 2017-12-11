package pl.edu.agh.kis.async_tasks;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.lang.ref.WeakReference;


public class DownloadAvatarTask extends AsyncTask<String, Void, Bitmap> {
    private WeakReference<ImageView> imageView;

    public DownloadAvatarTask(ImageView view) {
        this.imageView = new WeakReference<>(view);
    }

    @Override
    protected Bitmap doInBackground(String... emails) {
        return ImageFetcher.fetchAvatar(emails[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        ImageView view = this.imageView.get();
        if (view != null) {
            view.setImageBitmap(bitmap);
        }
    }
}
