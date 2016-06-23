package com.zezoz.textanim.component;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.zezoz.textanim.R;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Componente visual para el monto a cobrar.
 *
 * @crated Sebad
 */
public class AnimatedText extends LinearLayout {


    LinkedList<TextSwitcher> listE = new LinkedList<>();
    LinkedList<TextSwitcher> listD = new LinkedList<>();

    LinkedList<Integer> numbers = new LinkedList<>();

    FrameLayout.LayoutParams layoutParams;

    int inc = 0;

    Handler handler = new Handler();

    Random randomGenerator = new Random();

    TextView codigo;

    public AnimatedText(Context context, AttributeSet attr) {
        super(context, attr);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.composite_view, this);

        codigo = (TextView) findViewById(R.id.codigo);

        layoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0, -20, 0, 0);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        addTSEntero();

        addTSDecimal();
        addTSDecimal();

        reset();
    }


    public void addNumber(int newValue) {
        if (numbers.size() < 8) {
            numbers.push(newValue);
            renderList();
        }

    }

    public void remove() {
        if (!numbers.isEmpty()) {
            numbers.pop();
            renderList();
        }
    }

    private void resize() {

        Iterator<TextSwitcher> enterosIt = listE.iterator();
        Iterator<TextSwitcher> decimalesIt = listD.iterator();

        while (decimalesIt.hasNext()) {
            TextSwitcher ts = decimalesIt.next();
            ts.removeAllViews();
            setFactoryDecimal(ts);
            ts.forceLayout();
        }

        while (enterosIt.hasNext()) {
            TextSwitcher ts = enterosIt.next();
            ts.removeAllViews();
            setFactoryEntero(ts);
            ts.forceLayout();
        }

        LinkedList<TextSwitcher> list = new LinkedList<>();
        list.addAll(listE);
        list.addAll(listD);

        Iterator<TextSwitcher> tsIt = list.descendingIterator();
        Iterator<Integer> numbersIt = numbers.iterator();

        int random = ( randomGenerator.nextInt(3) * 50 ) + 100;
        while (tsIt.hasNext()) {
            TextSwitcher ts = tsIt.next();
            if (numbersIt.hasNext()) {
                Integer n = numbersIt.next();
                ts.setCurrentText("0");
                setTextDelayed(ts,n,random);
            } else {
                if (numbers.size() < 3) {
                    if (!((TextView) ts.getChildAt(0)).getText().equals("0") && !TextUtils.isEmpty(((TextView) ts.getChildAt(0)).getText())) {
                        ts.setCurrentText("0");
                    }
                }
            }
        }

    }

    private void renderList() {

        // resize();

        LinkedList<TextSwitcher> list = new LinkedList<>();
        list.addAll(listE);
        list.addAll(listD);

        Log.i("TAG", "W " + getMeasuredWidth());

        if (numbers.size() > list.size()) {
            // agrego ts
            if (numbers.size() > list.size()) {
                addTSEntero(true);
                list = new LinkedList<>();
                list.addAll(listE);
                list.addAll(listD);
            }
        }

        if (numbers.size() < list.size()) {
            if (list.size() > 3) {
                // saco ts
                removeTS();
                list = new LinkedList<>();
                list.addAll(listE);
                list.addAll(listD);
            }
        }

        Iterator<TextSwitcher> tsIt = list.descendingIterator();
        Iterator<Integer> numbersIt = numbers.iterator();

        int totalW = codigo.getWidth();
        while (tsIt.hasNext()) {
            TextSwitcher ts = tsIt.next();
            totalW += ts.getWidth();
            int random = ( randomGenerator.nextInt(4) * 50 ) + 100;
            if (numbersIt.hasNext()) {
                Integer n = numbersIt.next();
                setTextDelayed(ts, n, random);

            } else {
                if (numbers.size() < 3) {
                    if (!((TextView) ts.getChildAt(0)).getText().equals("0") && !TextUtils.isEmpty(((TextView) ts.getChildAt(0)).getText())) {
                        ts.setText("0");
                    }
                }
            }
        }
        Log.i("TAG", "W " + totalW * 1.1);
    }

    private void setTextDelayed(final TextSwitcher ts, final int number, final int milis) {
        handler.postDelayed(new Runnable() {
            public void run() {
                ts.setText(String.valueOf(number));
            }
        }, milis);
    }


    public void reset() {
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            if (v instanceof TextSwitcher) {
                ((TextSwitcher) v).setCurrentText("0");
            }
        }
    }


    private void setFactoryDecimal(TextSwitcher ts) {

        Log.i("TAG", "numbers" + numbers.size());
        codigo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
        setFactory(ts, 35, Typeface.SANS_SERIF);
        /*
        if (numbers.size() < 6) {
            codigo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 56);
            setFactory(ts, 56, Typeface.SANS_SERIF);
        }
        if (numbers.size() == 6) {
            codigo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 56);
            setFactory(ts, 43, Typeface.SANS_SERIF);
        }
        if (numbers.size() == 7) {
            codigo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 43);
            setFactory(ts, 35, Typeface.SANS_SERIF);
        }
        if (numbers.size() == 8) {
            codigo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
            setFactory(ts, 35, Typeface.SANS_SERIF);
        }*/

    }

    private void setFactoryEntero(TextSwitcher ts) {
        Log.i("TAG", "numbers" + numbers.size());
        setFactory(ts, 70, Typeface.create("sans-serif-light", Typeface.NORMAL));
        /*
        if (numbers.size() < 6) {
            setFactory(ts, 120, Typeface.create("sans-serif-light", Typeface.NORMAL));
        }
        if (numbers.size() == 6) {
            setFactory(ts, 90, Typeface.create("sans-serif-light", Typeface.NORMAL));
        }
        if (numbers.size() == 7) {
            setFactory(ts, 70, Typeface.create("sans-serif-light", Typeface.NORMAL));
        }
        if (numbers.size() == 8) {
            setFactory(ts, 70, Typeface.create("sans-serif-light", Typeface.NORMAL));
        }
        */
    }

    private void setFactory(TextSwitcher ts, final int unit, final Typeface tf) {


        // Declare the in and out animations and initialize them
        Animation in = AnimationUtils.loadAnimation(getContext(), R.anim.anim_in);
        Animation out = AnimationUtils.loadAnimation(getContext(), R.anim.anim_out);

        // set the animation type of textSwitcher
        ts.setInAnimation(in);
        ts.setOutAnimation(out);
        // Set the ViewFactory of the TextSwitcher that will create TextView object when asked
        ts.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                // create new textView and set the properties like clolr, size etc
                TextView myText = new TextView(getContext());
                myText.setTextSize(TypedValue.COMPLEX_UNIT_SP, unit);
                myText.setTypeface(tf);
                myText.setLayoutParams(layoutParams);
                return myText;
            }
        });

    }

    private void addTSDecimal() {
        TextSwitcher ts = new TextSwitcher(getContext());
        ts.setId(1234 + inc);
        addView(ts);
        setFactoryDecimal(ts);
        inc++;
        listD.add(ts);
    }

    private void removeTS() {
        if (inc > 3) {
            removeViewAt(getChildCount() - 3);
            TextSwitcher textSwitcher = listE.removeLast();
            String str = ((TextView) textSwitcher.getChildAt(0)).getText().toString();
            Log.i("TAG", "Removing " + str);
        }
    }

    private void addTSEntero() {
        TextSwitcher ts = new TextSwitcher(getContext());
        ts.setId(123 + inc);
        addView(ts);
        setFactoryEntero(ts);
        ts.setCurrentText("0");
        listE.add(ts);
    }

    private void addTSEntero(boolean dynamic) {
        final int pos = getChildCount() - 3;
        final TextSwitcher ts = new TextSwitcher(getContext());
        setFactoryEntero(ts);
        ts.setId(123 + inc);
        ts.setCurrentText("0");
        addView(ts, pos + 1);
        listE.addLast(ts);
        inc++;

    }
}
