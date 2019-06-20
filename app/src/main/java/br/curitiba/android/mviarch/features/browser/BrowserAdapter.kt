package br.curitiba.android.mviarch.features.browser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.curitiba.android.mviarch.R
import br.curitiba.android.mviarch.data.models.Project
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class BrowserAdapter : RecyclerView.Adapter<BrowserAdapter.ViewHolder>() {

    interface Listener {
        fun onBookmarkedProjectClicked(projectId: String)
        fun onProjectClicked(projectId: String)
    }

    private var list: List<Project> = emptyList()
    private var listener: Listener? = null

    fun setList(list: List<Project>) {
        this.list = list
        this.notifyDataSetChanged()
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        with(holder) {
            ownerNameText.text = item.ownerName
            projectNameText.text = item.fullName

            Glide.with(itemView.context)
                .load(item.ownerAvatar)
                .apply(RequestOptions.circleCropTransform())
                .into(avatarImage)

            val starResource = if (item.isBookmarked!!) R.drawable.ic_star_black else R.drawable.ic_star_border_black
            bookmarkedImage.setImageResource(starResource)

            itemView.setOnClickListener {
                if (item.isBookmarked)
                    listener?.onBookmarkedProjectClicked(item.id!!)
                else
                    listener?.onProjectClicked(item.id!!)
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var avatarImage: ImageView = view.findViewById(R.id.image_owner_avatar)
        var ownerNameText: TextView = view.findViewById(R.id.text_owner_name)
        var projectNameText: TextView = view.findViewById(R.id.text_project_name)
        var bookmarkedImage: ImageView = view.findViewById(R.id.image_bookmarked)
    }
}

