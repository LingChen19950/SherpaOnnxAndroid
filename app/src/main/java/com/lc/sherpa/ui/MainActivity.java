package com.lc.sherpa.ui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.lc.sherpa.R;

public class MainActivity extends AppCompatActivity {

    // 导航控制器,用于控制页面跳转
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 启用边缘到边缘显示(全屏模式,状态栏透明)
        EdgeToEdge.enable(this);
        // 加载布局
        setContentView(R.layout.activity_main);
        // 监听系统布局的变化
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            // 获取状态栏和导航栏的高度/宽度
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            // 给内容区域添加 padding,避免被系统栏遮挡
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            // 返回后,系统知道你已经适配了
            return insets;
        });

        // 初始化导航控制器
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }
    }

    /**
     * 处理"向上导航"(返回上一个页面)
     * 当用户点击 Toolbar 的返回箭头时调用
     *
     * @return true 表示已处理返回, false 表示使用默认行为
     */
    @Override
    public boolean onSupportNavigateUp() {
        if (navController != null) {
            return navController.navigateUp() || super.onSupportNavigateUp();
        }
        return super.onSupportNavigateUp();
    }

}