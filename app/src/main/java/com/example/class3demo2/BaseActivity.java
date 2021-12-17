package com.example.class3demo2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class BaseActivity extends AppCompatActivity {
    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
//כדי לחפש פרגמנטים בתצוגה
        NavHost navHost = (NavHost) getSupportFragmentManager().findFragmentById(R.id.base_navhost);
        navController = navHost.getNavController();
        //TODO: MARK
        NavigationUI.setupActionBarWithNavController(this,navController);//מחבר את הקונטרולר עם ה activity
        // זו מחלקה סטטית בעצם כל מעבר בין מסך למסך הכותרת תוצג ה fragment שמוצג
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.base_menu,menu);
        return true;
    }

    @Override //TODO: עם טפלתי או לא טפלתי באפליקציה
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (!super.onOptionsItemSelected(item)) {
            switch (item.getItemId()) {
                case android.R.id.home: // מגיע לנו בשפה ועושה אחורה
                    navController.navigateUp();
                    return true;
//                case R.id.menu_about:      //טופס את הלחיצה של כפתור זה, אני לא יודע באיזה פרגמנט אני נמצא כי אני נמצא בתוך האקטיביטי הnavhost יוכל לדעת
//                    navController.navigate(R.id.action_global_aboutFragment);
//                    return true;
                default:
                    NavigationUI.onNavDestinationSelected(item,navController);// עושה navigation לפי הכפתור שלחצנו עליו
                    // איך הוא מחבר בין הכפתור לפרגמנט - אז לפי הid של הmenu והid של הפרגמנט
//                case R.id.menu_add:
//                    break;
            }
        } else {
            return true;
        }
        return false;
    }
}
