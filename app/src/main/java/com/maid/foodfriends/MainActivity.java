package com.maid.foodfriends;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int score=0;
    TextView resut;
    int[] fruit={
        R.drawable.apple,
        R.drawable.banana,
        R.drawable.orange,
        R.drawable.strawberry,
        R.drawable.tomato,
    };
    ArrayList<ImageView> fruits = new ArrayList<>();
    Handler mHandler = new Handler();
    int time = 100;
    int notFruit=R.drawable.ic_launcher_background;
    int widthOfScreen;
    int widthOfBlock;
    int noOfBlock=8;
    int fruitDragged, fruitReplaced;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        resut = findViewById(R.id.result);
        resut.setText(String.valueOf(score));
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        widthOfScreen= displayMetrics.widthPixels;
        int heightOfScreen = displayMetrics.heightPixels;
        widthOfBlock= widthOfScreen/noOfBlock;
        board();
        for (ImageView imageView : fruits){
             imageView.setOnTouchListener(new OnSwipeListner(this){
                 @Override
                 void onSwipeLeft() {
                     super.onSwipeLeft();

                     fruitDragged=imageView.getId();
                     fruitReplaced=fruitDragged-1;
                     movement();


                 }

                 @Override
                 void onSwipeRight() {
                     super.onSwipeRight();

                     fruitDragged=imageView.getId();
                     fruitReplaced=fruitDragged +1;
                    movement();
                 }

                 @Override
                 void onSwipeUp() {
                     super.onSwipeUp();

                     fruitDragged=imageView.getId();
                     fruitReplaced=fruitDragged-noOfBlock;
                     movement();
                 }

                 @Override
                 void onSwipeDown() {
                     super.onSwipeDown();

                     fruitDragged=imageView.getId();
                     fruitReplaced=fruitDragged+noOfBlock;
                     movement();
                 }
             });
        }
        mHandler = new Handler();
        repeat();
    }

    private void board() {
        GridLayout gridLayout = findViewById(R.id.board);
        gridLayout.setColumnCount(noOfBlock);
        gridLayout.setRowCount(noOfBlock);
        gridLayout.getLayoutParams().width=widthOfScreen;
        gridLayout.getLayoutParams().height=widthOfScreen;
        for (int i = 0; i < noOfBlock*noOfBlock; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(widthOfBlock,widthOfBlock));
            imageView.setMaxHeight(widthOfBlock);
            imageView.setMaxWidth(widthOfBlock);
            int randomFruit = (int)Math.floor(Math.random() * fruit.length);
            imageView.setImageResource(fruit[randomFruit]);
            imageView.setTag(fruit[randomFruit]);
            fruits.add(imageView);
            gridLayout.addView(imageView);
        }



    }
    private void movement()
    {
        int background = (int)fruits.get((fruitReplaced)).getTag();
        int background1 = (int)fruits.get((fruitDragged)).getTag();
        fruits.get(fruitDragged).setImageResource(background);
        fruits.get(fruitReplaced).setImageResource(background1);
        fruits.get(fruitDragged).setTag(background);
        fruits.get(fruitReplaced).setTag(background1);
    }
    private void checkRow3(){
        for (int i = 0; i < 62; i++) {
                int choseFruit=(int)fruits.get(i).getTag();
                boolean isBlank=(int)fruits.get(i).getTag()==notFruit;
                Integer[] notValid={6,7,14,15,22,23,30,31,38,39,46,47,54,55};
                List<Integer> list = Arrays.asList(notValid);
                if(!list.contains(i)){
                    int x=i;
                    if((int)fruits.get(x++).getTag()==choseFruit&&!isBlank&&(int) fruits.get(x++).getTag()==choseFruit&&(int) fruits.get(x).getTag()==choseFruit){
                        score=score+3;
                        resut.setText(String.valueOf(score));
                        fruits.get(x).setImageResource(notFruit);
                        fruits.get(x).setTag(notFruit);
                        x--;
                        fruits.get(x).setImageResource(notFruit);
                        fruits.get(x).setTag(notFruit);
                        x--;
                        fruits.get(x).setImageResource(notFruit);
                        fruits.get(x).setTag(notFruit);
                        x--;


                    }
                }

        }
        moveCandies();
    }
    private void checkColumn3(){
        for (int i = 0; i < 47; i++) {
            int choseFruit=(int)fruits.get(i).getTag();
            boolean isBlank=(int)fruits.get(i).getTag()==notFruit;
            int x=i;
            if((int)fruits.get(x).getTag()==choseFruit&&!isBlank&&(int) fruits.get(x+noOfBlock).getTag()==choseFruit&&(int) fruits.get(x+2*noOfBlock).getTag()==choseFruit){
                score=score+3;
                resut.setText(String.valueOf(score));
                fruits.get(x).setImageResource(notFruit);
                fruits.get(x).setTag(notFruit);
                x=x+noOfBlock;
                fruits.get(x).setImageResource(notFruit);
                fruits.get(x).setTag(notFruit);
                x=x+noOfBlock;
                fruits.get(x).setImageResource(notFruit);
                fruits.get(x).setTag(notFruit);

            }

        }
        moveCandies();

    }
    private void checkRow4(){
        for (int i = 0; i < 62; i++) {
            int choseFruit=(int)fruits.get(i).getTag();
            boolean isBlank=(int)fruits.get(i).getTag()==notFruit;
            Integer[] notValid={6,7,14,15,22,23,30,31,38,39,46,47,54,55};
            List<Integer> list = Arrays.asList(notValid);
            if(!list.contains(i)){
                int x=i;
                if((int)fruits.get(x++).getTag()==choseFruit&&!isBlank&&(int) fruits.get(x++).getTag()==choseFruit&&(int) fruits.get(x).getTag()==choseFruit){
                    score=score+3;
                    resut.setText(String.valueOf(score));
                    fruits.get(x).setImageResource(notFruit);
                    fruits.get(x).setTag(notFruit);
                    x--;
                    fruits.get(x).setImageResource(notFruit);
                    fruits.get(x).setTag(notFruit);
                    x--;
                    fruits.get(x).setImageResource(notFruit);
                    fruits.get(x).setTag(notFruit);
                    x--;
                    fruits.get(x).setImageResource(notFruit);
                    fruits.get(x).setTag(notFruit);
                    x--;


                }
            }

        }
        moveCandies();
    }
    private void moveCandies(){
        Integer[] row1 = {0,1,2,3,4,5,6,7};
        List<Integer> list = Arrays.asList(row1);
        for (int i = 55; i >=0 ; i--) {
            if((int)fruits.get(i+noOfBlock).getTag()==notFruit){
                score=score+3;
                resut.setText(String.valueOf(score));
                fruits.get(i+noOfBlock).setImageResource((int)fruits.get(i).getTag());
                fruits.get(i+noOfBlock).setTag(fruits.get(i).getTag());
                fruits.get(i).setImageResource(notFruit);
                fruits.get(i).setTag(notFruit);

                if(list.contains(i) && (int) fruits.get(i).getTag()==notFruit){
                    int randomFruit = (int) Math.floor(Math.random()*fruit.length);
                    fruits.get(i).setImageResource(fruit[randomFruit]);
                    fruits.get(i).setTag(fruit[randomFruit]);

                }

            }
        }
        for (int i = 0; i < 8; i++) {
            if((int) fruits.get(i).getTag()==notFruit){
                int randomFruit = (int) Math.floor(Math.random()*fruit.length);
                fruits.get(i).setImageResource(fruit[randomFruit]);
                fruits.get(i).setTag(fruit[randomFruit]);
            }
        }
    }
    Runnable repeatChecker = new Runnable() {
        @Override
        public void run() {
            try {
                checkRow3();
                checkRow4();
                checkColumn3();
                moveCandies();
            }finally {
                mHandler.postDelayed(repeatChecker,time);
            }
        }
    };
    void repeat(){
        repeatChecker.run();
    }
}