package com.lbx.mycustomtab.tab3;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lbx.mycustomtab.R;

/**
 * Created with IntelliJ IDEA.
 * Author: wangjie  email:wangjie@cyyun.com
 * Date: 13-6-14
 * Time: 下午2:39
 */
public class TabCFm extends Fragment{
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        System.out.println("CCCCCCCCCC____onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("CCCCCCCCCC____onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("CCCCCCCCCC____onCreateView");
        View view = inflater.inflate(R.layout.tab_c, container, false);
        GridView gv = (GridView)view.findViewById(R.id.members_gridview);
        MAdapter adapter = new MAdapter(this.getActivity());
        gv.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("CCCCCCCCCC____onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("CCCCCCCCCC____onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("CCCCCCCCCC____onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("CCCCCCCCCC____onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("CCCCCCCCCC____onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("CCCCCCCCCC____onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("CCCCCCCCCC____onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("CCCCCCCCCC____onDetach");
    }
}
class MAdapter extends BaseAdapter{

	private Context mContext;
	private LayoutInflater inflater;
	
	
	public MAdapter(Context mContext) {
		super();
		this.mContext = mContext;
		inflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 40;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder mHolder = null;
		if(convertView == null){
			mHolder = new Holder();
			convertView = inflater.inflate(R.layout.item, null);
			mHolder.iv = (ImageView)convertView.findViewById(R.id.iv);
			mHolder.tv = (TextView)convertView.findViewById(R.id.tv);
			convertView.setTag(mHolder);
		}else{
			mHolder = (Holder)convertView.getTag();
		}
		mHolder.iv.setImageResource(R.drawable.tabsearch);		
		mHolder.tv.setText(""+position);		
		return convertView;
	}
	class Holder{
		ImageView iv;
		TextView tv;
	}
}





