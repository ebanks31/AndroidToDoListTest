package com.example.android.rssfeed;


import com.example.android.rssfeed.MyListFragment.OnItemSelectedListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * 
 * This class keeps track of the detail Fragments. 
 * It keeps track of the list items that associated to an individual spinner title
 * 
 * @author Eric
 *
 */
public class DetailFragment extends ListFragment  implements OnItemClickListener {

	detailFragmentSelectedListener listener;
	CheckBox checkbox;
	private ListView contests_listView;
	  /* (non-Javadoc)
	 * @see android.app.Fragment#onActivityCreated(android.os.Bundle)
	 * 
	 * Sets initial list for detail fragments. Sets List Adaptter and creates view for Detail Fragment
	 */
	@Override
	  public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
	        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
	        "Linux", "OS/2" };
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
	        android.R.layout.simple_list_item_1, values);
	    setListAdapter(adapter);
	    

	    listener.detailFragmentSelected(values,adapter);
	    
	    getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

	    
	    
	    
	    
	  }

	
	  /* (non-Javadoc)
	 * @see android.app.ListFragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 * 
	 * Sets the initial values in the spinner.
	 */
	@SuppressWarnings("unchecked")
	@Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	      Bundle savedInstanceState) {
		
		 return inflater.inflate(R.layout.list_fragment, null, false);
	}
	
	

	  /* (non-Javadoc)
	 * @see android.app.Fragment#onAttach(android.app.Activity)
	 * 
	 * Attaches activity to detailfragment listerner
	 */
	@Override
	    public void onAttach(Activity activity) {
	      super.onAttach(activity);
	      if (activity instanceof OnItemSelectedListener) {
	        listener = (detailFragmentSelectedListener) activity;
	      } else {
	        throw new ClassCastException(activity.toString()
	            + " must implement MyListFragment.OnItemSelectedListener");
	      }
	    }
	  
	  /* (non-Javadoc)
	 * @see android.app.ListFragment#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 * 
	 * This method invokes when an individual list item is clicked.
	 */
	@Override
	  public void onListItemClick(ListView l, View v, int position, long id) {
	    // do something with the data
		// Creating alert Dialog with one Button
			 AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

            // Setting Dialog Title
            alertDialog.setTitle("PASSWORD");

            // Setting Dialog Message
            alertDialog.setMessage("Enter Password");
             final EditText input = new EditText(getActivity());  
  			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT);
  			input.setLayoutParams(lp);
  			alertDialog.setView(input); // uncomment this line

            // Setting Icon to Dialog
          //  alertDialog.setIcon(R.drawable.key); 

            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("YES",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            // Write your code here to execute after dialog
							
							//Checks if edittext is empty,space, or null. Not a valid list item.
			if(input.equals("") || input.equals(" ") || input==null)
			{
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

            // Setting Dialog Title
            alertDialog.setTitle("PASSWORD");

            // Setting Dialog Message
            alertDialog.setMessage("Please Enter a valid list name");
			}
			else
			{
		  //Add to Spinner
		 Spinner spinner =  (Spinner)getView().findViewById(R.id.spinner1);
		 ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, android.R.id.text1);
		 spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		 spinner.setAdapter(spinnerAdapter);
		 spinnerAdapter.add(input.getText().toString());
		 spinnerAdapter.notifyDataSetChanged();
		
		 }
		 
                        }
                        });
            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("NO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog
                            dialog.cancel();
                        }
                    });

            // closed

            // Showing Alert Message
            alertDialog.show();
        };
		 //Load item into fragment. Load ListFragment.
		 

	  
	  
	  	/* (non-Javadoc)
	  	 * @see android.app.Fragment#onCreateContextMenu(android.view.ContextMenu, android.view.View, android.view.ContextMenu.ContextMenuInfo)
	  	 * 
	  	 * Adds menus when detail Fragment is long clicked
	  	 * 
	  	 */
	  	@Override
public void onCreateContextMenu(ContextMenu menu, View v,
    ContextMenuInfo menuInfo) {
			
    menu.setHeaderTitle("Context Menu");  
    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
    //menu.setHeaderTitle(Countries[info.position]);
    String[] menuItems = getResources().getStringArray(R.array.country_arrays);
    for (int i = 0; i<menuItems.length; i++) {
      menu.add(Menu.NONE, i, i, menuItems[i]);
    }
  
}

	    // Container Activity must implement this interface
	    /**
	     * Interface for detail fragment. This interface allow communication between detail fragment and Main Activity Class
	     * @author Eric
	     *
	     */
	    public interface detailFragmentSelectedListener {
	        public void detailFragmentSelected(String[] values,ArrayAdapter<String> adapter);
	    }

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			System.out.println("hek");
		}
	  	
} 