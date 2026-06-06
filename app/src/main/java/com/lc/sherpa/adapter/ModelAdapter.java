package com.lc.sherpa.adapter;

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
        return new VH(View.inflate(parent.getContext(), R.layout.item_model, null));
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int pos) {
        ModelInfo m = list.get(pos);
        h.name.setText(m.name);
        h.info.setText(m.type + " • " + m.size);

        if (m.isDefault) {
            h.status.setText("✅ 默认模型");
            h.status.setTextColor(0xFF4CAF50);
        } else if (m.isDownloaded) {
            h.status.setText("✅ 已下载");
            h.status.setTextColor(0xFF2196F3);
        } else {
            h.status.setText("❌ 未下载");
            h.status.setTextColor(0xFF757575);
        }

        h.download.setVisibility(m.isDownloaded ? View.GONE : View.VISIBLE);
        h.use.setVisibility(m.isDownloaded && !m.isDefault ? View.VISIBLE : View.GONE);
        h.delete.setVisibility(m.isDownloaded && !m.isDefault ? View.VISIBLE : View.GONE);

        h.download.setOnClickListener(v -> listener.onDownload(m));
        h.use.setOnClickListener(v -> listener.onSetDefault(m));
        h.delete.setOnClickListener(v -> listener.onDelete(m));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class VH extends RecyclerView.ViewHolder {
        TextView name, info, status;
        MaterialButton download, use, delete;

        public VH(View v) {
            super(v);
            name = v.findViewById(R.id.tv_model_name);
            info = v.findViewById(R.id.tv_model_info);
            status = v.findViewById(R.id.tv_status);
            download = v.findViewById(R.id.btn_download);
            use = v.findViewById(R.id.btn_use);
            delete = v.findViewById(R.id.btn_delete);
        }
    }
}