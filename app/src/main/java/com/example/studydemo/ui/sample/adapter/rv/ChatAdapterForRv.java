package com.example.studydemo.ui.sample.adapter.rv;

import android.content.Context;

import com.example.studydemo.base.baseadapter.MultiItemTypeAdapter;
import com.example.studydemo.ui.sample.bean.ChatMessage;

import java.util.List;


/**
 * Created by zhy on 15/9/4.
 */
public class ChatAdapterForRv extends MultiItemTypeAdapter<ChatMessage>
{
    public ChatAdapterForRv(Context context, List<ChatMessage> datas)
    {
        super(context, datas);

        addItemViewDelegate(new MsgSendItemDelagate());
        addItemViewDelegate(new MsgComingItemDelagate());
    }
}
