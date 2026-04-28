package com.lc.sherpa.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.lc.sherpa.R;

public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 跳转到实时识别
        view.findViewById(R.id.card_live_asr).setOnClickListener(v -> {
            Log.d(TAG, "onViewCreated: 跳转到实时识别");
            Navigation.findNavController(v).navigate(R.id.action_to_asr);
        });

        // 跳转到语音合成
        view.findViewById(R.id.card_tts).setOnClickListener(v -> {
            Log.d(TAG, "onViewCreated: 跳转到语音合成");
            Navigation.findNavController(v).navigate(R.id.action_to_tts);
        });

        // 跳转到模型管理
        view.findViewById(R.id.card_model).setOnClickListener(v -> {
            Log.d(TAG, "onViewCreated: 跳转到模型管理");
            Navigation.findNavController(v).navigate(R.id.action_to_model);
        });
    }
}
