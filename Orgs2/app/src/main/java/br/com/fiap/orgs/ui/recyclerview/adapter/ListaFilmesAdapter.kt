package br.com.fiap.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.orgs.R
import br.com.fiap.orgs.model.Filme

class ListaFilmesAdapter(
    private val context: Context,
    private val filmes: List<Filme>
) : RecyclerView.Adapter<ListaFilmesAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            fun vincula(filme: Filme) {
                val nome = itemView.findViewById<TextView>(R.id.nome)
                nome.text = filme.nome

                val imagem: ImageView = itemView.findViewById<ImageView>(R.id.imagem)
                imagem.setImageResource(filme.imagem)

                val descricao = itemView.findViewById<TextView>(R.id.descricao)
                descricao.text = filme.descricao

            }
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ListaFilmesAdapter.ViewHolder {
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.produto_item, parent, false)
            return ListaFilmesAdapter.ViewHolder(view)
        }

        override fun getItemCount(): Int = filmes.size

            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                val filme = filmes[position]
                holder.vincula(filme)
            }
        }
