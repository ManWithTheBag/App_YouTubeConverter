package org.doit.youtubeconverterapp.fragment;

import android.app.DownloadManager;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.doit.youtubeconverterapp.MainActivity;
import org.doit.youtubeconverterapp.R;
import org.doit.youtubeconverterapp.model.youTubeContent.general.YouTubeContentModelGeneral;


public class DownloadContentFragment extends Fragment {
    private TextView title;
    private ImageView image;
    private Button button_download;
    private YouTubeContentModelGeneral contentModelGeneral;
    private DownloadManager downloadManager;


    public DownloadContentFragment(YouTubeContentModelGeneral contentModelGeneral, DownloadManager downloadManager) {
        this.contentModelGeneral = contentModelGeneral;
        this.downloadManager = downloadManager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_download_content, container, false);
        title = v.findViewById(R.id.title);
        image = v.findViewById(R.id.image);
        button_download = v.findViewById(R.id.button_download);

        title.setText(contentModelGeneral.getTitle());
        loadImage(contentModelGeneral.getImage());

        button_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadFile(contentModelGeneral);
            }
        });

        return v;
    }

    private void loadImage(String imgUrl){
        Glide.with(DownloadContentFragment.this)
                .load(imgUrl)
                .centerCrop()
                .into(image);
    }

    private void downloadFile(YouTubeContentModelGeneral contentModelGeneral){
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(contentModelGeneral.getDownloadUrl()));
        request.setTitle(contentModelGeneral.getTitle());
        request.setDescription("Downloading: " + contentModelGeneral.getTitle() );
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.allowScanningByMediaScanner();
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, contentModelGeneral.getTitle() + contentModelGeneral.getExtensionFile());

        downloadManager.enqueue(request);
    }
}