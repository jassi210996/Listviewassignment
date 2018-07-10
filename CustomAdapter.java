package in.codingninjas.envision.expensemanager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CustomAdapter extends ArrayAdapter {

    Context mContext;
    ArrayList<Item> itemsList;
    LayoutInflater inflater;


    // Constructor
    public CustomAdapter(Context context, ArrayList<Item> itemsList) {
        super(context, 0);
        mContext = context;
        this.itemsList = itemsList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // This function return the number of different type of views that will be there in the list view.
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    // This function returns the type of item(in our case header or list item) that adapter wants to know in getView function.
    @Override
    public int getItemViewType(int position) {
        Item item = itemsList.get(position);
        return item.getItemType();
    }

    // This function gives the total count of items that will be there in the list.
    @Override
    public int getCount() {

        return itemsList.size();
    }

    // This function returns the object of the itemList that has to inflated at that position.
    @Override
    public Object getItem(int position) {

        Item item = itemsList.get(position);
        return item;
    }


    // This function returns the unique id associated with every inflated layout, since it's is not useful in our case so
    // we return the position, which is also unique for every item.
    @Override
    public long getItemId(int position) {

        return position;
    }


    // This is the function in which we have to inflate the layout as per its TYPE
    // this function gets the type of each item from getItemViewType and on the basis of it we apply if else and inflate the layout.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View output = convertView;
        int type = getItemViewType(position);

        if (type == 0) {
            if (output == null) {

                output = inflater.inflate(R.layout.header_item_layout, parent, false);
                TextView t = output.findViewById(R.id.headerTitleTextView);
                ExpenseViewHolder viewHolder = new ExpenseViewHolder();
                viewHolder.date = t;
                output.setTag(viewHolder);

            }

            ExpenseViewHolder viewHolder = (ExpenseViewHolder) output.getTag();
            HeaderItem header = (HeaderItem) itemsList.get(position);
            viewHolder.date.setText(header.getHeaderTitle());
        } else if (type == 1) {

            if (output == null) {
                output = inflater.inflate(R.layout.expense_row_layout, parent, false);

                TextView nameTextView = output.findViewById(R.id.expenseName);
                TextView amountTextView = output.findViewById(R.id.expenseAmount);
                TextView dateTextView = output.findViewById(R.id.expenseDate);
                TextView timeTextView = output.findViewById(R.id.expenseTime);
                ExpenseViewHolder viewHolder = new ExpenseViewHolder();
                viewHolder.title = nameTextView;
                viewHolder.amount = amountTextView;
                viewHolder.date = dateTextView;
                viewHolder.time = timeTextView;
                output.setTag(viewHolder);
            }
            ExpenseViewHolder viewHolder = (ExpenseViewHolder) output.getTag();
            Expense expense = (Expense) itemsList.get(position);
            viewHolder.title.setText(expense.getName());
            viewHolder.amount.setText(expense.getAmount() + " Rs");
            viewHolder.date.setText(expense.getDate());
            viewHolder.time.setText(expense.getTime());

        }
        return output;
    }


}