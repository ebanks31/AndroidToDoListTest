package com.example.android.rssfeed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class CustomAdapter extends ArrayAdapter<ListItem>{
	ListItem[] modelItems = null;
 Context context;
 DetailFragment detailfragment;
 final ArrayList<CheckBox> checkboxlist;
 public static ArrayList<CheckBox> currentcheckboxlist = new ArrayList<CheckBox>(); 
 
 public CustomAdapter(Context context, ListItem[] resource,DetailFragment detailfragment) {
 super(context,android.R.layout.simple_list_item_1,resource);
 // TODO Auto-generated constructor stub
 this.context = context;
 this.modelItems = resource;
 this.detailfragment = detailfragment;
 checkboxlist = new ArrayList<CheckBox>();
 
 }
 
	/**
	 * Gets the date
	 * @return date returns date
	 */
	public ArrayList<CheckBox> getCurrentcheckboxlist() {	
		return currentcheckboxlist;
	}

	/**
	 * Set date
	 * @param date date that will be set
	 */
	public void setCurrentcheckboxlist(ArrayList<CheckBox>  currentcheckboxlist) {
		CustomAdapter.currentcheckboxlist=currentcheckboxlist;
	}
	
	
 
 static class ViewHolder {
	  TextView text;
	  CheckBox checkbox;
	}
 
 
 @Override
 public View getView(int position, View convertView, ViewGroup parent) {
 // TODO Auto-generated method stub
 LayoutInflater inflater = ((Activity)context).getLayoutInflater();
 ViewHolder holder = new ViewHolder();
 if(convertView==null){

 convertView = inflater.inflate(R.layout.fragment_rssitem_detail, parent, false); 
 holder.text = (TextView) convertView.findViewById(R.id.detailsText);
 holder.checkbox = (CheckBox) convertView.findViewById(R.id.checkBox1);
 
 convertView.setTag(holder);
 }
 else {
     holder = (ViewHolder) convertView.getTag();
 }
 
 CheckBox cb = holder.checkbox;
 TextView text =holder.text;
 
 checkboxlist.add(cb);
 
 
                      
 text.setText(modelItems[position].getListItem());
 if(modelItems[position].getValue() == 1)
 cb.setChecked(true);
 else
 cb.setChecked(false);
 
// final ListView listview = (ListView) convertView.findViewById(android.R.id.list);
 final ListView listview = detailfragment.getListView();
 this.notifyDataSetChanged();
    cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		System.out.println("hello");
	}

 });
 
 
    detailfragment.getListView().setOnItemClickListener(new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // TODO Auto-generated method stub
            Log.d("############","Items " );
            
            /*
            for(CheckBox list:checkboxlist)
            {
            	list.setChecked(true);
            }*/
            System.out.println("Size: " + checkboxlist.size());
            CheckBox currentcheckbox = checkboxlist.get(checkboxlist.size()-3);
            ArrayList<CheckBox> checkboxlist2 = new ArrayList<CheckBox>();
            
            int iterator = checkboxlist.size() - modelItems.length;
            
            
            
            
            for(int i=iterator;i<checkboxlist.size();i++)
            {
            	CheckBox checkbox = checkboxlist.get(i);
            	
            	currentcheckboxlist.add(checkboxlist.get(i));
            }
            
            CheckBox currentcheckbox1 = currentcheckboxlist.get(position);
            
            if(currentcheckbox1.isChecked())
            {
            	currentcheckboxlist.get(position).setChecked(false);
            	currentcheckbox1.setChecked(false);
            }
            else
            {
            	currentcheckbox1.setChecked(true);
            	currentcheckboxlist.get(position).setChecked(true);
            }
            
            
           // currentcheckboxlist.add(currentcheckbox1);
            setCurrentcheckboxlist(currentcheckboxlist);
           /*
            if(modelItems[position].getValue() == 0)
            	 cb.setChecked(true);
            	 else
            	 cb.setChecked(false);*/
            
       	 System.out.println("hello111");
        }

    });
    
 
 
 OnClickListener myhandler1 = null;
 
 /*
 name.setOnClickListener(new View.OnClickListener(){
     @Override
     public void onClick(View v) {
         // do you work here
    
      }
     });*/
	        
		  
 return convertView;
 }
 
 @Override
 public int getCount() {
     return modelItems.length;
 }
}
