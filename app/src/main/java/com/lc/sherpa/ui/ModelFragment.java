package com.lc.sherpa.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lc.sherpa.R;
import com.lc.sherpa.adapter.ModelAdapter;
import com.lc.sherpa.model.ModelInfo;
import com.lc.sherpa.utils.DownloadUtils;

import java.util.ArrayList;
import java.util.List;

public class ModelFragment extends Fragment {

    private static final String TAG = "ModelFragment";

    private RecyclerView recycler;
    private ModelAdapter adapter;
    private List<ModelInfo> modelList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup c, Bundle b) {
        return inflater.inflate(R.layout.fragment_model, c, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView(view);
        initClick(view);
    }

    private void initData() {
        DownloadUtils.updateModelsJson(new DownloadUtils.DownloadCallback() {
            @Override
            public void onSuccess(String content) {
                Log.d(TAG, "onSuccess: " + content);
            }

            @Override
            public void onFailure(String error) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    private void initView(@NonNull View view) {
        recycler = view.findViewById(R.id.recycler_models);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ModelAdapter(modelList, null);
        recycler.setAdapter(adapter);
    }

    private static void initClick(@NonNull View view) {
        FloatingActionButton fabImport = view.findViewById(R.id.fab_import);
        fabImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: click fabImport");
            }
        });
    }

}