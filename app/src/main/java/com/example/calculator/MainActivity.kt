package com.example.calculator

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.text.isDigitsOnly
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {

    private lateinit var text : TextView;
    private var tempText = ""

    private lateinit var sevenButton: Button;
    private lateinit var eathButton: Button;
    private lateinit var nineButton: Button;
    private lateinit var fourButton: Button;
    private lateinit var fiveButton: Button;
    private lateinit var sixButton: Button;
    private lateinit var oneButton: Button;
    private lateinit var twoButton: Button;
    private lateinit var threeButton: Button;
    private lateinit var zeroButton: Button;

    private lateinit var delButton: Button;
    private lateinit var cButton: Button;

    private lateinit var plusButton : Button;
    private lateinit var minusButton: Button;
    private lateinit var xButton : Button;
    private lateinit var delitButton : Button;
    private lateinit var prochentButton : Button;
    private lateinit var piButton: Button;
    private  lateinit var ravnoButton: Button;

    private var znakActivate = false;
    private lateinit var znaks : Button
    private var cc = 0;

    private var count = 0;
    private var newSize = 0
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        sevenButton = findViewById(R.id.seven);
        eathButton = findViewById(R.id.eath);
        nineButton = findViewById(R.id.nine);
        fourButton = findViewById(R.id.four);
        fiveButton = findViewById(R.id.five);
        sixButton = findViewById(R.id.six);
        oneButton = findViewById(R.id.one);
        twoButton = findViewById(R.id.two);
        threeButton = findViewById(R.id.three);
        zeroButton = findViewById(R.id.zero);
        cButton = findViewById(R.id.c);
        delButton = findViewById(R.id.del);
        ravnoButton = findViewById(R.id.ravno);
        plusButton = findViewById(R.id.plus);
        minusButton = findViewById(R.id.minus);
        xButton = findViewById(R.id.x);
        delitButton = findViewById(R.id.delit);
        prochentButton = findViewById(R.id.procent);
        piButton = findViewById(R.id.pi);

        fun returnColor(button: Button)
        {
                button.setBackgroundResource(R.drawable.backgtounds);
                button.setTextColor(Color.parseColor("#FFFFFF"));
        }
        fun setColor(button: Button)
        {
            button.setBackgroundResource(R.drawable.backch);
            button.setTextColor(Color.parseColor("#DF8713"));
        }
        fun strRaz(str: String): String {
            var h = ""
            var j = 1
            for (i in str.length - 1 downTo 0) {
                if (str[i] == ' ') continue
                if (j == 3) {
                    h += ' '
                    j = 0
                }
                h += str[i]
                j++
            }

            var str2 = ""
            for (i in h.length - 1 downTo 0) {
                str2 += h[i]
            }

            return str2
        }



        text = findViewById(R.id.text);


        if(count == 0)
            newSize = text.textSize.toInt() - 188;

        fun returnString(str : String) : String
        {
            var tempStr = "";
            for (i in str)
            {
                if(i != ' ')
                {
                    tempStr += i;
                }
            }
            return tempStr;
        }

        fun returnTrue(formatStr1 : String,formatStr2 : String) : Int
        {
            var otvet = 0;
            if(znaks.text.toString() == "x"){
                otvet = formatStr2.toInt() * formatStr1.toInt();
            }
            else if(znaks.text.toString() == "-") {
                otvet = formatStr2.toInt() - formatStr1.toInt();
            }
            else if(znaks.text.toString() == "+") {
                otvet = formatStr2.toInt() + formatStr1.toInt();
            }
            else {
                otvet = formatStr2.toInt() / formatStr1.toInt();
            }
            return otvet;
        }

        ravnoButton.setOnClickListener{view: View ->
            ravnoButton.setBackgroundResource(R.drawable.backch);
            ravnoButton.setTextColor(Color.parseColor("#DF8713"))
            if(znakActivate)
            {
                var formatStr1 = returnString(text.text.toString());
                var formatStr2 = returnString(tempText);

                if(formatStr1 == "") {formatStr1=formatStr2};
                Toast.makeText(this,count.toString(),Toast.LENGTH_SHORT).show();

                newSize = 75;
                var otvet = returnTrue(formatStr1,formatStr2);
                count = otvet.toString().length;
                var strTemplat = "";
                if(count > 12)
                {
                    var count2 = 0;
                    while(count2 != otvet.toString().length-1)
                    {
                        strTemplat += otvet.toString()[count2];
                        count2++;
                    }

                }
                else
                    strTemplat = otvet.toString();
                if(count >= 7 && count < 9)
                    newSize -= 15;
                else if(count > 9)
                    newSize -= 30;
                text.setTextSize(newSize.toFloat());
                cc = 0;
                count = 0;
                var strItog = strRaz(strTemplat);
                text.setText(strItog);
                znakActivate = false;
                returnColor(znaks);


            }
            Handler(Looper.getMainLooper()).postDelayed({
                ravnoButton.setTextColor(Color.parseColor("#FFFFFF"));
            },200)
            Handler(Looper.getMainLooper()).postDelayed({
                ravnoButton.setBackgroundResource(R.drawable.background3);
            }, 200)
        }

        val everyoneButtonNumbers = View.OnClickListener { view: View ->
            val but = (view as Button);
            if(znakActivate && cc == 0)
            {
                count = 0;
                newSize = 75;
                text.setText("");
                text.setTextSize(TypedValue.COMPLEX_UNIT_SP, newSize.toFloat())
                cc++;
            }
            but.setBackgroundResource(R.drawable.whenclick);
            if(count == 12){

            }
            else {
                val cimbol = (view as Button).text.toString();
                if (count == 0)
                    text.setText("");

                var str2 = strRaz(text.text.toString());
                var str = str2 + cimbol;
                if (count == 7 || count == 9 || count == 12) {
                    if (count == 7)
                        newSize -= 15;
                    else if (count == 9)
                        newSize -= 15;

                    text.setTextSize(TypedValue.COMPLEX_UNIT_SP, newSize.toFloat())
                }
                count = count + 1;
                text.setText(str);
            }
            Handler(Looper.getMainLooper()).postDelayed({
                but.setBackgroundResource(R.drawable.background3);
            }, 200)
        }

        val digitButtons = listOf<Button>(sevenButton, eathButton, nineButton, fourButton,
            fiveButton, sixButton, oneButton, twoButton,threeButton, zeroButton)
        digitButtons.forEach { button ->
            button.setOnClickListener(everyoneButtonNumbers)
        }




        val everyoneZnak = View.OnClickListener {view: View ->
            var but = (view as Button);
            if(!znakActivate)
            {
                setColor(but);
                tempText= text.text.toString();
                znakActivate = true;
                znaks = but;
            }
            else
            {
                if(cc == 0)
                {
                    returnColor(znaks);
                    setColor(but);
                    znaks = but;
                }
                else
                {
                    var formatStr1 = returnString(text.text.toString());
                    var formatStr2 = returnString(tempText);
                    if(formatStr1 == "") {formatStr1=formatStr2};
                    newSize = 75;
                    var otvet = returnTrue(formatStr1,formatStr2);
                    count = otvet.toString().length;
                    if(count >= 7 && count < 9)
                        newSize -= 15;
                    else if(count > 9)
                        newSize -= 30;
                    text.setTextSize(newSize.toFloat());
                    cc = 0;
                    text.setText(otvet.toString());
                    znakActivate = false;
                    returnColor(znaks);

                }
            }


        }


        var znakButtons = listOf<Button>(plusButton,minusButton,xButton,piButton,prochentButton,delitButton);
        znakButtons.forEach { button ->
            button.setOnClickListener(everyoneZnak);
        }
        piButton.setOnClickListener { view: View ->
            var but = (view as Button)
            but.setBackgroundResource(R.drawable.whenclick);


            Handler(Looper.getMainLooper()).postDelayed({
                but.setBackgroundResource(R.drawable.background3);
            }, 200)
        }

        cButton.setOnClickListener{view: View ->
            if(znakActivate)
            {
                returnColor(znaks);
                znakActivate = false;
                cc = 0;
            }
            var but = (view as Button)
            but.setBackgroundResource(R.drawable.whenclick2)
            count = 0;
            newSize = 75;
            text.setTextSize(newSize.toFloat());
            text.setText("0");
            Handler(Looper.getMainLooper()).postDelayed({
                but.setBackgroundResource(R.drawable.background2);
            }, 200)
        }
        prochentButton.setOnClickListener{view: View ->
            var but = (view as Button)
            var number: Int
            but.setBackgroundResource(R.drawable.whenclick2);
            if(count > 0) {
                var str = "";
                var i = 0;
                var tempStr = text.text;
                while (i != tempStr.length) {
                    if (tempStr[i] != ' ') {
                        str += tempStr[i];
                    }
                    i++;
                }
                number = str.toInt()
                number /= 100;
                text.setText(number.toString());
            }
            Handler(Looper.getMainLooper()).postDelayed({
                but.setBackgroundResource(R.drawable.background2);
            }, 200)

        }
        delButton.setOnClickListener{view: View ->
            var but = (view as Button)
            but.setBackgroundResource(R.drawable.whenclick2)
            if(count == 0){}
            else if(count==1){count = 0; text.setText("0")}
            else
            {
                if (count == 8) {
                    newSize += 15;
                } else if (count == 10) {
                    newSize += 15;
                }

                var str2 = text.text.toString();
                var col = str2.length-1;
                if(str2[str2.length-1] == ' ')
                {
                    col--;
                }
                var temp = "";
                var i = 0;
                while(i != col)
                {
                    temp += str2[i];
                    i++;
                }
                text.setTextSize(newSize.toFloat());
                text.setText(temp);
                count -= 1;
            }
            Handler(Looper.getMainLooper()).postDelayed({
                but.setBackgroundResource(R.drawable.background2);
            }, 200)
        }

    }
}
