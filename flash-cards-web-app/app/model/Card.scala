package model

import java.sql.Date

/**
  * @author Vitaliy_Zinchenko
  */
case class Card(
                 id: Option[Long],
                 word: String,
                 translation: String,
                 cardSetId: Long,
                 level: Int,
                 guessDate: Date,
                 trainDate: Date
               )
