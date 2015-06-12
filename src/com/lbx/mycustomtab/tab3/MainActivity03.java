package com.lbx.mycustomtab.tab3;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.lbx.mycustomtab.R;

public class MainActivity03 extends FragmentActivity {
    /**
     * Called when the activity is first created.
     */
    private RadioGroup rgs;
    public List<Fragment> fragments = new ArrayList<Fragment>();

    Fragment a,b,c,d,e;
    public String hello = "hello ";
    TabPagerAdapter adapter;
    private MViewPager mViewPager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);

        a = new TabAFm();
        b = new TabBFm();
        c = new TabCFm();
        d = new TabDFm();
        e = new TabEFm();
        fragments.add(a);
        fragments.add(b);
        fragments.add(c);
        fragments.add(d);
        fragments.add(e);

        rgs = (RadioGroup) findViewById(R.id.tabs_rg);

        mViewPager = (MViewPager) findViewById(R.id.viewPager);
        
        //可以控制MViewPager的左右滑动
        mViewPager.setScanScroll(true);
        
        adapter = new TabPagerAdapter(getSupportFragmentManager(), fragments);
      //设置Adapter
      	mViewPager.setAdapter(adapter);
      	 
      	mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				Log.i("infos", "onPageSelected---"+arg0);
//				rgs.getChildAt(arg0).setSelected(true);
				rgs.check(getCheckedId(arg0));
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
      	rgs.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				int index = getIndex(checkedId);
				reInit1();
				mViewPager.setCurrentItem(index);
				Log.i("infos", "onCheckedChanged---"+checkedId+" / index = "+index);
				 
			}
		});
    }

    public void reInit(){
    	fragments.remove(e);
    	adapter.setList(fragments);
    }
    public void reInit1(){
    	if(!fragments.contains(e)){
    		fragments.add(e);
    	}
    	adapter.setList(fragments);
    }
    private int getIndex(int checkedId){
    	if(checkedId == R.id.tab_rb_a)return 0;
    	if(checkedId == R.id.tab_rb_b)return 1;
    	if(checkedId == R.id.tab_rb_c)return 2;
    	if(checkedId == R.id.tab_rb_d)return 3;
    	if(checkedId == R.id.tab_rb_e)return 4;
    	return 0;
    }
    private int getCheckedId(int index){
    	if(index == 0)return R.id.tab_rb_a;
    	if(index == 1)return R.id.tab_rb_b;
    	if(index == 2)return R.id.tab_rb_c;
    	if(index == 3)return R.id.tab_rb_d;
    	if(index == 4)return R.id.tab_rb_e;
    	return 0;
    }
}
class TabPagerAdapter extends FragmentStatePagerAdapter {
	private List<Fragment> list;
	
	
	public void setList(List<Fragment> list) {
		this.list = list;
		this.notifyDataSetChanged();
	}

	//构造函数
	public TabPagerAdapter(FragmentManager fm, List<Fragment> list) {
		super(fm);
		this.list = list;
	}

	@Override
	public Fragment getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		//LogUtil.e("infos", "TabPagerAdapter---position = "+position+" / object = "+object);
		//重写此方法，做空处理，即可
		//super.destroyItem(container, position, object);
	}

	@Override
	public Parcelable saveState() {
		// TODO Auto-generated method stub
		return super.saveState();
	}

}
