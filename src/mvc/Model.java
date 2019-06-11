package mvc;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<View> myViews = new ArrayList<>();

    public void addView(View view) {
        this.myViews.add(view);
    }

    public void changed() {
        myViews.forEach(View::modelChanged);
    }
}
