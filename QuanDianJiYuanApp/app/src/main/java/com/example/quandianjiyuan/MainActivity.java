package com.example.quandianjiyuan;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int statusBarHeight = getStatusBarHeight();



        // 初始化ViewPager2和BottomNavigationView
        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // 设置ViewPager2适配器
        viewPager.setAdapter(new ViewPagerAdapter(this));

        // 禁用ViewPager2滑动
        viewPager.setUserInputEnabled(false);

        // 底部导航栏点击事件
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    viewPager.setCurrentItem(0);
                } else if (itemId == R.id.nav_search) {
                    viewPager.setCurrentItem(1);
                } else if (itemId == R.id.nav_favorites) {
                    viewPager.setCurrentItem(2);
                } else if (itemId == R.id.nav_notifications) {
                    viewPager.setCurrentItem(3);
                } else if (itemId == R.id.nav_profile) {
                    viewPager.setCurrentItem(4);
                }
                return true;
            }
        });

        // ViewPager2页面切换监听
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.nav_home);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.nav_search);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.nav_favorites);
                        break;
                    case 3:
                        bottomNavigationView.setSelectedItemId(R.id.nav_notifications);
                        break;
                    case 4:
                        bottomNavigationView.setSelectedItemId(R.id.nav_profile);
                        break;
                }
            }
        });
    }

    // ViewPager2适配器
    private static class ViewPagerAdapter extends FragmentStateAdapter {
        public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            // 返回对应的Fragment
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new SearchFragment();
                case 2:
                    return new FavoritesFragment();
                case 3:
                    return new NotificationsFragment();
                case 4:
                    return new ProfileFragment();
                default:
                    return new HomeFragment();
            }
        }

        @Override
        public int getItemCount() {
            return 5; // 五个页面
        }

    }
    private int getStatusBarHeight() {
        int result = 0;
        @SuppressLint("InternalInsetResource") int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}