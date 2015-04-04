package com.example.android.rssfeed;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
/**
 * 
 * This class is a Custom OnItemSelectedListener for the spinner. 
 * It contains actions for when the item is selected in the spinner
 *  
 * @author Eric
 *
 */
public class CustomOnItemSelectedListener extends MyListFragment implements OnItemSelectedListener {
 
	Context context;
	Spinner spinnertitles;
	ArrayAdapter<String> spinnerAdapter;
	ArrayList<String> spinnerlist;
	private OnItemSelectedListener listener;
  
	/**
	 * Overload constructor of CustomOnItemSelectedListener class. 
	 * Sets intial values that are needed for the CustomOnItemSelectedListener
	 * 
	 * @param context set the context from the Main Activity class
	 * @param listspinner Current Spinner from the MyListFragment Class
	 * @param dataAdapter Adapter that is associated to the spinner
	 * @param spinnerlist List of spinner items that are located int he spinner
	 */
	public CustomOnItemSelectedListener(Context context, Spinner listspinner,
			ArrayAdapter<String> dataAdapter,ArrayList<String> spinnerlist) {
		// TODO Auto-generated constructor stub
    	this.spinnertitles=listspinner;
    	this.context=context;
    	this.spinnerAdapter=dataAdapter;
    	this.spinnerlist=spinnerlist;
	}


	/**
	 * Default Constructor for CustomOnItemSelectedListener
	 */
	public CustomOnItemSelectedListener() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.example.android.rssfeed.MyListFragment#onItemSelected(android.widget.AdapterView, android.view.View, int, long)
	 * 
	 * Method is invoked when a spinner title is selected.
	 * Invokes action depending on what spinner title is selected.
	 */
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
            long id) {
         final String itemselected = parent.getItemAtPosition(pos).toString();
         
         MyListFragment.currentspinner=itemselected;
		 if(itemselected.equals("New List"))
		 {
			 AlertDialog.Builder alertDialog =null;

		     // Creating alert Dialog with one Button
			 alertDialog = new AlertDialog.Builder(context);

            // Setting Dialog Title
            alertDialog.setTitle("List Title");

            // Setting Dialog Message
            alertDialog.setMessage("Enter title for your list");
             final EditText input = new EditText(context);  
  			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT);
  			input.setLayoutParams(lp);
  			alertDialog.setView(input); 

		   String listinput = input.getText().toString();
		    

            // Setting Positive "Yes" Button
            alertDialog.setNegativeButton("YES",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            // Write your code here to execute after dialog

             String listinput = input.getText().toString();
			//Checks if edittext is empty,space, or null. Not a valid list item.
			if(listinput.equals("") || listinput.equals(" ") || input==null)
			{
				
				 AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            // Setting Dialog Title
            alertDialog.setTitle("Invalid Name");

            // Setting Dialog Message
            alertDialog.setMessage("Please Enter a valid list name");
            setPositiveAlertOptionOK(alertDialog);
            alertDialog.show();
				}

			else
			{
			
		 if(!checkDuplicates(spinnerlist,listinput)) //check duplicate spinner items
		 {
		 spinnerAdapter.add(listinput);
		 spinnerAdapter.notifyDataSetChanged();
		 spinnertitles.setAdapter(spinnerAdapter); 

		 int spinnerPosition = spinnerAdapter.getPosition(listinput);

		 //set the selected spinner item according to value
		 spinnertitles.setSelection(spinnerPosition);
		 MyListFragment.currentspinner=listinput;
		 MyListFragment.staticlistener.clearFragmentList(1);

		 }
		 else
		 {
			 	//Spinner item is a duplicated
			 	AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
	            // Setting Dialog Title
	            alertDialog.setTitle("Duplicate item");

	            // Setting Dialog Message
	            alertDialog.setMessage("Cannot add duplicate item");
	            setPositiveAlertOptionOK(alertDialog);
	            alertDialog.show();

		 }
		 
		 }
			
		 
                        }}
                        );
            setPositiveAlertOptionNO(alertDialog);

    		// create alert dialog
            AlertDialog alertDialog1 = alertDialog.create();

			// show alert dialog
			alertDialog1.show();
        }
		 
			else if(itemselected.equals("Sample List"))
			{
				MyListFragment.staticlistener.sampleFragmentList(1);
		
			}
			else
			{
				//If Spinner is a an spinner item in the list created by the user
			         FeedReaderDbHelper db = new FeedReaderDbHelper(context);
					 List<ListItem> itemlistbytitle = db.getAllListItemsByTitle(itemselected);
					 ArrayList<String> values =getListItems(itemlistbytitle);
					 
					 MyListFragment.staticlistener.updateList(values);
			}
			}
				
    
		
	/**
	 * 
	 * Gets List Items by Spinner Title
	 * 
	 * @param itemlistbytitle List of ListItems
	 * @return ArrayList of String containing list items by spinner title.
	 */
	public ArrayList<String> getListItems(List<ListItem> itemlistbytitle)
	{
		ArrayList<String> finallist = new ArrayList<String>();
		for(ListItem listitem:itemlistbytitle)
		{
			finallist.add(listitem.getListItem());
		}
		return finallist;
	}
	
	/**
	 * 
	 * Get List Item Objects by spinner title
	 * 
	 * @param itemlist List of List Item Objects
	 * @param title selected Spinner title
	 * @return List ArrayList of string containing List Items by spinner title
	 */
	public ArrayList<String> getListTitleFromListItems(List<ListItem> itemlist, String title)
	{
		ArrayList<String> finallist = new ArrayList<String>();
		for(ListItem listitem:itemlist)
		{
			String currenttitle = listitem.getTitle();
			if(currenttitle!=null)
			{
			if(currenttitle.equals(title))
			{
			finallist.add(listitem.getListItem());
			}
			}
		}
			
		return finallist;
	}
	
	/**
	 * 
	 * Get List of title from List of List Item Objects
	 * 
	 * @param itemlistbytitle List of List Item Objects
	 * @return ArrayList of string containing spinner titles.
	 */
	public ArrayList<String> getListTitles(List<ListItem> itemlistbytitle)
	{
		ArrayList<String> finallist = new ArrayList<String>();
		for(ListItem listitem:itemlistbytitle)
		{
			finallist.add(listitem.getTitle());
		}
		return finallist;
	}
		 
	/**
	 * 
	 * Check for duplicates in an ArrayList of String
	 * 
	 * @param arraylist ArrayList of String containing spinner titles
	 * @param input spinner title
	 * @return ArrayList of String containing spinner titles with duplicates removed
	 */
	public boolean checkDuplicates(ArrayList<String> arraylist,String input)
	{
		for(String listitem: arraylist)
		{
			if(listitem.equals(input))
			{
				return true;
			}
		}
		return false;
		
	}
    
 
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
 
    }
 
    /**
     * Set Positive Alert Button with String No
     * 
     * @param alertDialog Alert Dialog that is passed in 
     */
    public void setPositiveAlertOptionNO(AlertDialog.Builder alertDialog)
    {
        // Setting Negative "NO" Button
        alertDialog.setPositiveButton("NO",
                new DialogInterface.OnClickListener() {
                    /* (non-Javadoc)
                     * @see android.content.DialogInterface.OnClickListener#onClick(android.content.DialogInterface, int)
                     */
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        dialog.cancel();
                    }
                });
    }
    
    /**
     * Set Positive Alert Button with String OK
     * 
     * @param alertDialog Alert Dialog that is passed in 
     */
    public void setPositiveAlertOptionOK(AlertDialog.Builder alertDialog)
    {
        // Setting Negative "NO" Button
        alertDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        dialog.cancel();
                    }
                });
    }
    
    
    /**
     * 
     * Interface for communication between CustomerOnItemselectLister and other fragment and activities.
     * 
     * @author Eric
     *
     */
    public interface OnItemSelectedListener {
	    public void onRssItemSelected(int position);
	      public void sampleFragmentList(int position);
	    }
}