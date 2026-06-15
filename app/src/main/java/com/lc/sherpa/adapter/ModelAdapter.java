package com.lc.sherpa.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.lc.sherpa.R;
import com.lc.sherpa.model.ModelInfo;

import java.util.List;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.VH> {

    public interface ModelActionListener {
        void onDownload(ModelInfo model);

        void onSetDefault(ModelInfo model);

        void onDelete(ModelInfo model);
    }

    private final List<ModelInfo> list;
    private final ModelActionListener listener;

    public ModelAdapter(List<ModelInfo> list, ModelActionListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_model, parent, false);
        return new VH(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull VH h, int pos) {
        ModelInfo m = list.get(pos);
        h.name.setText(m.name);
        h.info.setText(m.type + " • " + m.size);

        h.download.setEnabled(!m.isDownloaded);
        h.use.setEnabled(m.isDownloaded && !m.isDefault);
        h.delete.setEnabled(m.isDownloaded && !m.isDefault);

        h.download.setOnClickListener(v -> listener.onDownload(m));
        h.use.setOnClickListener(v -> listener.onSetDefault(m));
        h.delete.setOnClickListener(v -> listener.onDelete(m));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class VH extends RecyclerView.ViewHolder {
        TextView name, info;
        MaterialButton download, use, delete;

        public VH(View v) {
            super(v);
            name = v.findViewById(R.id.tv_model_name);
            info = v.findViewById(R.id.tv_model_info);
            download = v.findViewById(R.id.btn_download);
            use = v.findViewById(R.id.btn_use);
            delete = v.findViewById(R.id.btn_delete);
        }
    }
}