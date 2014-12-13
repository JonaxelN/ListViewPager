package viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jonaxel.listviewpager.R;

/**
 * Created by jonathan on 29/10/14.
 */
public class Fragment1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }

        return (LinearLayout) inflater.inflate(R.layout.viewpager_fragment1, container, false);
    }
}
