package xyz.markapp.markmvvmtest1.model


import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.annotations.SerializedName

class DeveloperModel() {
/*
    {
        "login": "jvantuyl",
        "id": 101,
        "node_id": "MDQ6VXNlcjEwMQ==",
        "avatar_url": "https://avatars.githubusercontent.com/u/101?v=4",
        "gravatar_id": "",
        "url": "https://api.github.com/users/jvantuyl",
        "html_url": "https://github.com/jvantuyl",
        "followers_url": "https://api.github.com/users/jvantuyl/followers",
        "following_url": "https://api.github.com/users/jvantuyl/following{/other_user}",
        "gists_url": "https://api.github.com/users/jvantuyl/gists{/gist_id}",
        "starred_url": "https://api.github.com/users/jvantuyl/starred{/owner}{/repo}",
        "subscriptions_url": "https://api.github.com/users/jvantuyl/subscriptions",
        "organizations_url": "https://api.github.com/users/jvantuyl/orgs",
        "repos_url": "https://api.github.com/users/jvantuyl/repos",
        "events_url": "https://api.github.com/users/jvantuyl/events{/privacy}",
        "received_events_url": "https://api.github.com/users/jvantuyl/received_events",
        "type": "User",
        "site_admin": false
    },*/

    @SerializedName("login")
    var login: String? = null

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("avatar_url")
    var avatar_url: String? = null

    @SerializedName("url")
    var url: String? = null //persion information

    @SerializedName("html_url")
    var html_url: String? = null

    @SerializedName("type")
    var type: String? = null

    //---
    //persion
    var user: User? = null

    /* //users/xxx:  persion information
    {
  "login": "jvantuyl",
  "id": 101,
  "node_id": "MDQ6VXNlcjEwMQ==",
  "avatar_url": "https://avatars.githubusercontent.com/u/101?v=4",
  "gravatar_id": "",
  "url": "https://api.github.com/users/jvantuyl",
  "html_url": "https://github.com/jvantuyl",
  "followers_url": "https://api.github.com/users/jvantuyl/followers",
  "following_url": "https://api.github.com/users/jvantuyl/following{/other_user}",
  "gists_url": "https://api.github.com/users/jvantuyl/gists{/gist_id}",
  "starred_url": "https://api.github.com/users/jvantuyl/starred{/owner}{/repo}",
  "subscriptions_url": "https://api.github.com/users/jvantuyl/subscriptions",
  "organizations_url": "https://api.github.com/users/jvantuyl/orgs",
  "repos_url": "https://api.github.com/users/jvantuyl/repos",
  "events_url": "https://api.github.com/users/jvantuyl/events{/privacy}",
  "received_events_url": "https://api.github.com/users/jvantuyl/received_events",
  "type": "User",
  "site_admin": false,
  "name": "Jayson Vantuyl",
  "company": null,
  "blog": "http://souja.net",
  "location": "San Francisco, California, USA",
  "email": null,
  "hireable": true,
  "bio": null,
  "twitter_username": "jvantuyl",
  "public_repos": 61,
  "public_gists": 15,
  "followers": 40,
  "following": 12,
  "created_at": "2008-01-30T01:11:50Z",
  "updated_at": "2021-11-01T22:33:07Z"
}
     */

    companion object {


        /*      @JvmStatic
              @BindingAdapter("avatar_url")
              fun loadImage(imageView: ImageView, imageURL: String) {

                  Log.e("imsgeurl", imageURL)
                  Glide.with(imageView.context)
                      .setDefaultRequestOptions(
                          RequestOptions()
                              .circleCrop()
                      )
                      .load(imageURL)
                      .into(imageView)
              }*/

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun bindImage(imageView: ImageView, url: String?) {
            val context: Context = imageView.context
            Glide.with(context)
                .setDefaultRequestOptions(
                    RequestOptions()
                        .circleCrop()
                )
                .load(url)
                .into(imageView)
        }

        @JvmStatic
        @BindingAdapter("no_name", "login_name", "developerModel")
        fun get_name_with_id(view: TextView, id: Int?, login: String?, develermodel: DeveloperModel?) {
            var txt: String = id.toString() + ". " + (develermodel?.type ?: "-") + "\n" + login + "\n"
            view.setText(txt)
        }


        @JvmStatic
        @BindingAdapter("login_name")
        fun get_name(view: TextView, login: String?) {
            var txt: String = login + ""
            view.setText(txt)
        }


        @JvmStatic
        @BindingAdapter("fullname")
        fun getfullname(view: TextView, model: DeveloperModel?) {
            var txt: String = model?.user?.name + ""
            view.setText(txt)
        }

        @JvmStatic
        @BindingAdapter("location")
        fun getLocation(view: TextView, model: DeveloperModel?) {
            var txt: String = model?.user?.location + ""
            view.setText(txt)
        }

        @JvmStatic
        @BindingAdapter("blog")
        fun getBlog(view: TextView, model: DeveloperModel?) {
            var txt: String = model?.user?.blog + ""
            view.setText(txt)
        }


    }


}
