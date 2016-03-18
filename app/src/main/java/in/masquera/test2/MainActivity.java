package in.masquera.test2;
/*
    Run in Android Studio.
    Run using app config. Default should work :)

 */
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.Menu;
import android.widget.TextView;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class MainActivity extends Activity {
    String htmlText = "it failed";
    String url = "http://en.wikipedia.org/";
    //This is the example string if you want to use it. The htmlTextView will work if you pass it texterino instead of getHtmlCode()in the last line of onCreate
    String texterino =  "<div id=\"content_events\">\n" +
            "              <h2><a href=\"http://www.hawaii.edu/calendar/manoa/\" class=\"head_link\">Events</a> </h2>\n" +
            "              <div id=\"todayatmanoa\"><div id=\"tam_header\"><img src=\"/images/template_images/todayshighlights.gif\" alt=\"Today at UH Manoa\" width=\"256\" height=\"14\" /></div><div id=\"tam_box\">\n" +
            "<span class=\"content_header\">March 17, 2016 &ndash; 8:30 am</span><br />\n" +
            "<a href=\"http://www.hawaii.edu/calendar/manoa/2016/03/17/28471.html\" class=\"content_link\">Helmet Giveaway and Trauma Injury Prevention</a><br /><br /><span class=\"content_header\">March 17, 2016 &ndash; 10:00 am</span><br />\n" +
            "<a href=\"http://www.hawaii.edu/calendar/manoa/2016/03/17/28460.html\" class=\"content_link\">SPAS Asia Graduate Students Conference</a><br /><br /><span class=\"content_header\">March 17, 2016 &ndash; 3:15 pm</span><br />\n" +
            "<a href=\"http://www.hawaii.edu/calendar/manoa/2016/03/17/28442.html\" class=\"content_link\">DarkSide experiment by Jelena Maricic</a><br /><br /><span class=\"content_header\">March 17, 2016 &ndash; 4:30 pm</span><br />\n" +
            "<a href=\"http://www.hawaii.edu/calendar/manoa/2016/03/17/28296.html\" class=\"content_link\">Lecture by Andrea Dezsö, Intersections Visiting Artist</a><br /><br /><span class=\"content_header\">March 17, 2016 &ndash; 4:30 pm</span><br />\n" +
            "<a href=\"http://www.hawaii.edu/calendar/manoa/2016/03/17/28414.html\" class=\"content_link\">Financing Your Graduate Education</a><br /><br /><span class=\"content_header\">March 17, 2016 &ndash; 4:30 pm</span><br />\n" +
            "<a href=\"http://www.hawaii.edu/calendar/manoa/2016/03/17/28453.html\" class=\"content_link\">Native American Voices on Climate Change and Natural Hazards</a><br /><br /></div></div><br />\n" +
            "          </div></div></div>";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Auto generated
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView htmlTextView = (TextView) findViewById(R.id.html_text);
        //Grabs text as html code, gets images if it encounters certain strings, nothing
        //If this is commented out, it means that the crawly isn't working currently.
     /*   try {
            htmlTextView.setText(Html.fromHtml(getHtmlCode(), new ImageGetter(), null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        htmlTextView.setText(Html.fromHtml(texterino,new ImageGetter(),null));
    }


    private String getHtmlCode()throws Exception{

            //Trying to connect to the given url. Tried inputting straight Strings also, but still broken. Could be compatibility issue.
            Document doc = Jsoup.connect(url).get();
            //dailyEvents will be the elements in the div called "todayatmanoa"
            Elements dailyEvents = doc.select("div#todayatmanoa");
            //making it into a string as HTML
            htmlText = dailyEvents.html();
            //return htmlText;

            return htmlText;
    }

    private class ImageGetter implements Html.ImageGetter {
        //If there's an image in the parsed html text, it will populate the <img> with an image found in the drawables folder
        public Drawable getDrawable(String source) {
            int id;
            //Will change this to images that exist on the manoa page for the event announcements
            if (source.equals("/images/template_images/todayshighlights.gif")) {
                //Need to put an image named test in the drawable folder for this to work.
                id = R.drawable.todayshighlights;
            }

            else {
                return null;
            }

            Drawable d = getResources().getDrawable(id);
            //Grab the size of the image
            d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            return d;
        }

    }
}

