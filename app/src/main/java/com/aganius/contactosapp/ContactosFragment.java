package com.aganius.contactosapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import com.aganius.contactosapp.logica.Contacto;
import com.aganius.contactosapp.modelo.DatabaseHandler;
import com.aganius.contactosapp.util.ContactosAdapter;
import com.aganius.contactosapp.util.XmlParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class ContactosFragment extends ListFragment {

    private OnFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ContactosFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        leerContactos(getActivity());


        // TODO: Change Adapter to display your content
//        setListAdapter(new ArrayAdapter<>(getActivity(),
//                android.R.layout.simple_list_item_1, android.R.id.text1, ContactosContent.CONTACTOS));

        ContactosAdapter adapter = new ContactosAdapter(getActivity());
        setListAdapter(adapter);

    }

    private void leerContactos(Context context) {
        try {
            XmlParser.parseXML(context);

        } catch (XmlPullParserException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
//            Log.d("Position", "Position: " + position + ", Size: " + ContactosContent.CONTACTOS.size());
//            mListener.onFragmentInteraction(ContactosContent.CONTACTOS.get(position).getNombre());

            DatabaseHandler databaseHandler = new DatabaseHandler(getActivity());
            Contacto contacto = databaseHandler.buscarContacto(position + 1);
            databaseHandler.close();
            mListener.onFragmentInteraction(contacto);

        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Contacto contacto);
    }

}
