package com.mgiandia.library.view.Returns.ManageReturns;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

import com.mgiandia.library.R;
import com.mgiandia.library.memorydao.BorrowerDAOMemory;
import com.mgiandia.library.memorydao.LoanDAOMemory;
import com.mgiandia.library.util.Quadruple;
import com.mgiandia.library.view.Util.AdvancedListAdapter;

/**
 * @author Νίκος Σαραντινός
 *
 * Υλοποιήθηκε στα πλαίσια του μαθήματος Τεχνολογία Λογισμικού το έτος 2016-2017 υπό την επίβλεψη του Δρ. Βασίλη Ζαφείρη.
 *
 */

public class ManageReturnsActivity extends AppCompatActivity implements ManageReturnsView, SearchView.OnQueryTextListener
{
    ManageReturnsPresenter presenter;

    private ListView itemListView;
    private SearchView searchListView;
    private AdvancedListAdapter adapter;

    /**
     * Δημιουργεί to layout και αρχικοποιεί
     * το activity.
     * @param savedInstanceState το Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_items);

        adapter = new AdvancedListAdapter(this);

        itemListView = (ListView) findViewById(R.id.item_list_view);
        itemListView.setAdapter(adapter);
        itemListView.setTextFilterEnabled(true);

        searchListView = (SearchView) findViewById(R.id.items_list_search_view);
        searchListView.setIconifiedByDefault(false);
        searchListView.setOnQueryTextListener(this);

        presenter = new ManageReturnsPresenter(this, new LoanDAOMemory(), new BorrowerDAOMemory());

        findViewById(R.id.item_add_new).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                presenter.onAddNewItem();
            }
        });

        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                presenter.onClickItem(((Quadruple)parent.getItemAtPosition(position)).getUID());
            }
        });
    }

    /**
     * Τροποποιεί το κείμενο.
     * @param text Το κείμενο που θα τροποποιηθεί
     * @return true
     */
    public boolean onQueryTextChange(String text)
    {
        if (TextUtils.isEmpty(text))
            itemListView.clearTextFilter();
        else
            itemListView.setFilterText(text);

        return true;
    }

    /**
     * Υποβάλλει το κείμενο.
     * @param query Θέτει το κείμενο ως query
     * @return false
     */
    public boolean onQueryTextSubmit(String query)
    {
        return false;
    }

    /**
     * Αδείαζει το κείμενο που βρίσκεται
     * μέσα στην μπάρα αναζήτησης.
     */
    private void clear_search_bar()
    {
        searchListView.setQuery("", false);
        searchListView.clearFocus();
        presenter.onLoadSource();
    }

    /**
     * Φορτώνει την λίστα με τις επιστροφές.
     * @param input Η λιστα που θα φορτώσει
     */
    public void loadSource(List<Quadruple> input)
    {
        adapter.loadSource(input);
    }

    /**
     * Κατα την αλλαγή της κατάστασης ενός δανίου
     * εμφανίζεται ένα μήνυμα μορφής alert.
     * @param uid To μοναδικό id του δανιζόμενου
     * @param title Ο τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    public void newLoanStateSelectAlert(int uid, String title, String message)
    {
        final int tmp = uid;

        new AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Επιστρέψτε",
            new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    presenter.onChangeItemState(tmp, true);
                }
            })
            .setNeutralButton("Ακύρωση",
            new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                }
            })
            .setNegativeButton("Χάθηκε",
            new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    presenter.onChangeItemState(tmp, false);
                }
            }).create().show();
    }

    /**
     * Κατα την ανανέωση της σελίδας
     * διαγράφετε η μπάρα αναζήτησης
     */
    public void refresh()
    {
        clear_search_bar();
    }

    /**
     * Εμφανίζει ένα μήνυμα με περιεχόμενο
     * value.
     * @param value To περιεχόμενο που θα εμφανιστεί
     */
    public void showToast(String value)
    {
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }

    /**
     * Εμφανίζει ένα μήνυμα με περιεχόμενο
     * value σε μορφή alert.
     * @param title O τίτλος του μηνύματος
     * @param message Το περιεχόμενο του μηνύματος
     */
    public void showAlert(String title, String message)
    {
        new AlertDialog.Builder(this).setCancelable(true).setTitle(title).setMessage(message).setPositiveButton(R.string.ok, null).create().show();
    }

    /**
     * Επιστρέφει το id του δανιζόμενου.
     * @return Το id του δανιζόμενου
     */
    public int getAttachedBorrowerID()
    {
        return this.getIntent().getExtras().getInt("borrower_id");
    }

    /**
     * Θέτει το όνομα της σελίδας.
     * @param value το όνομα της σελίδας
     */
    public void setPageName(String value)
    {
        getSupportActionBar().setTitle(value);
    }
}
