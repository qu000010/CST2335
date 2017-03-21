package com.example.zhen.androidlabs;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MessageFragment extends Fragment {
    String msg;
    Long msgID;

    ChatWindow chatw = null;

    public MessageFragment(){

    }

    public MessageFragment (ChatWindow c){
        chatw = c;
    }
    //no matter how you got here, the data is in the getArguments
    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        Bundle bun = getArguments();
        msg = bun.getString("Message");
        msgID = bun.getLong("ID");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View gui = inflater.inflate(R.layout.fragment_layout, null);
        TextView message = (TextView)gui.findViewById(R.id.messageHere);
        message.setText(msg);
        TextView id = (TextView)gui.findViewById(R.id.messageID);
        id.setText("ID:" + msgID);

        Button btnDelete = (Button)gui.findViewById(R.id.deleteMessage);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("MessageFragment", "User clicked Delete Message button");
                if (chatw == null) {               // called from phone
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("DeleteID", msgID);
                    getActivity().setResult(Activity.RESULT_OK, resultIntent);
                    getActivity().finish();
                }
                else            // callled from tablet
                {
                    chatw.deleteListMessage(msgID);
                    chatw.removeFragment();
                }
            }
        });

        return gui;
    }

}
