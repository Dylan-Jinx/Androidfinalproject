package com.demo.jinx.finalproject.ui.home.web;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.demo.jinx.finalproject.R;
import com.demo.jinx.finalproject.base.BaseFragment2;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;

public class WebFragment extends BaseFragment2 {
    private AgentWeb agentWeb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_web,container,false);
        agentWeb = AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) root, new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                )).useDefaultIndicator()
                .setWebChromeClient(new WebChromeClient())
                .createAgentWeb()
                .ready()
                .go(getArguments() != null ? getArguments().getString("url"):"http://www.baidu.com");
        return root;
    }

    @Override
    public void onPause() {
        agentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        agentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        agentWeb.getWebLifeCycle().onDestroy();
        super.onDestroyView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return agentWeb.handleKeyEvent(keyCode, event);
    }
}
