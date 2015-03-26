package com.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.corehandle.command.BaseCommand;
import com.corehandle.command.FetchCrashInfoCommand;
import com.corehandle.command.FetchStoryInfoCommand;
import com.corehandle.controller.BaseController;
import com.corehandle.controller.CommandExecutor;
import com.corehandle.receiver.BaseReceiver;
import com.corehandle.receiver.FetchCrashInfoReceiver;
import com.corehandle.receiver.FetchStoryInfoReceiver;
import com.model.R;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment())
//                    .commit();
//        }
        
        loadCommand();
    }

    
    
    public void loadCommand() {
    	LinearLayout layout=new LinearLayout(this);
    	this.setContentView(layout);
    	
    	CommandExecutor.getCommandExecutor();
    	
    	Button button=new Button(this);
    	button.setText("≤‚ ‘√¸¡Ó÷¥––");
    	button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				handleFetchStoryInfoCommand();
				handleFetchCrashInfoCommand();
			}
		});
    	
    	layout.addView(button);
    }
    
    public void handleFetchStoryInfoCommand() {
    	BaseReceiver mReceiver = new FetchStoryInfoReceiver();
    	
    	BaseCommand mCommand = new FetchStoryInfoCommand();
    	mCommand.setTag("fetch_story_tag");
    	mCommand.setReceiver(mReceiver);
    	
    	BaseController controller=new BaseController();
    	controller.setCommand(mCommand);
    	controller.execute();
    }
    
    public void handleFetchCrashInfoCommand() {
    	BaseReceiver mReceiver = new FetchCrashInfoReceiver();
    	BaseCommand mCommand = new FetchCrashInfoCommand();
    	mCommand.setTag("fetch_crash_tag");
    	mCommand.setReceiver(mReceiver);
    	BaseController controller=new BaseController();
    	controller.setCommand(mCommand);
    	controller.execute();
    }
    
    public void step1() {
    	int a=0;
    	int b=0;
    	int c=10;
    }
    

    public void step2() {
    	int b=3;
    	int c=40;
    	int a=20;
    	int d=65;
    	int e=90;
    	int f=80;
    	
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
