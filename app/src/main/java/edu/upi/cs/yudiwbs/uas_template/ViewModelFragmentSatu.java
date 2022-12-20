package edu.upi.cs.yudiwbs.uas_template;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelFragmentSatu extends ViewModel {
    public MutableLiveData<String> data;
    public ViewModelUserPref() {
        data = new MutableLiveData<String>();
        data.setValue(null);
    }

    public LiveData<String> getData() {
        return data;
    }

    public void setData (String state) {
        data.setValue(state);
    }
}

