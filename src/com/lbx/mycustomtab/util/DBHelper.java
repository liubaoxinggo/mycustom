package com.lbx.mycustomtab.util;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.lbx.mycustomtab.TApplication;
import com.lbx.mycustomtab.entity.Fav;
import com.lbx.mycustomtab.entity.User;

import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalDb.DbUpdateListener;

public class DBHelper {

	private static DBHelper dbHelper;
	private  FinalDb fb;// = FinalDb.create(TApplication.nowApplication, targetDirectory, dbName, isDebug, dbVersion, dbUpdateListener);
	private String dbName = "UCall_DB";
	private boolean isDebug = true;
	private int dbVersion = 3;
	private String targetDirectory = Environment.getExternalStorageDirectory().getAbsolutePath()+"/ucall/";
	private DbUpdateListener dbUpdateListener = new DbUpdateListener() {

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			System.out.println(oldVersion+"<-old------new->"+newVersion);
			if(newVersion == 2){
				db.execSQL("alter table com_lbx_mycustomtab_entity_User add Tags text");
				db.execSQL("alter table com_lbx_mycustomtab_entity_Fav add Tags text");
			} else if(newVersion == 3){
				db.execSQL("alter table com_lbx_mycustomtab_entity_User add age Integer");
//				db.execSQL("alter table com_lbx_mycustomtab_entity_Fav add Tags text");
			} 
		}
		
	};
	
	public DBHelper() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("´´½¨DBHelperÊµÀý");
		fb = FinalDb.create(TApplication.nowApplication, targetDirectory, dbName, isDebug, dbVersion, dbUpdateListener);
//		fb = FinalDb.create
	}
	public static DBHelper getInstance(){
		if(dbHelper == null){
			synchronized (DBHelper.class) {
				if(dbHelper == null){
					dbHelper = new DBHelper();
				}
			}
		}
		return dbHelper;
	}
	public void saveUser(final User u){
		System.out.println("thread  = "+Thread.currentThread());
		ThreadPoolManager.getInstance().addTask(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				fb.save(u);
			}
		});
	}
	public void saveFav(final Fav f){
		ThreadPoolManager.getInstance().addTask(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				fb.saveBindId(f);
			}
		});
	}
}
















