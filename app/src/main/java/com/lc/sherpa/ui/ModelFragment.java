package com.lc.sherpa.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.lc.sherpa.utils.ModelsJsonManager;

import java.util.ArrayList;
import java.util.List;

public class ModelFragment extends Fragment {

    private static final String TAG = "ModelFragment";

    private ModelAdapter adapter;
    private final List<ModelInfo> modelList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup c, Bundle b) {
        return inflater.inflate(R.layout.fragment_model, c, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
        initView(view);
        initClick(view);
    }

    private void initView(@NonNull View view) {
        modelList.addAll(ModelsJsonManager.getInstance().getModels());
        RecyclerView recycler = view.findViewById(R.id.recycler_models);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ModelAdapter(modelList, new ModelAdapter.ModelActionListener() {
            @Override
            public void onDownload(ModelInfo model) {
                Log.d(TAG, "onDownload: ");
            }

            @Override
            public void onSetDefault(ModelInfo model) {
                Log.d(TAG, "onSetDefault: ");
            }

            @Override
            public void onDelete(ModelInfo model) {
                Log.d(TAG, "onDelete: ");
            }
        });
        recycler.setAdapter(adapter);
    }

    private void initClick(@NonNull View view) {
        FloatingActionButton fabImport = view.findViewById(R.id.fab_import);
        fabImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: click fabImport");

            }
        });
        TextView tvUpdate = view.findViewById(R.id.tv_update);
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: click tvUpdate");
                updateFromNetwork();
            }
        });
    }

    private void updateFromNetwork() {
        ModelsJsonManager.getInstance().updateFromNetwork(new DownloadUtils.DownloadCallback() {
            @Override
            public void onSuccess(String content) {
                updateAdapter();
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void updateAdapter() {
        modelList.clear();
        modelList.addAll(ModelsJsonManager.getInstance().getModels());
        if (getActivity() ==  null) {
            return;
        }
        getActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());
    }

}