package model

/**
  * @author Vitaliy_Zinchenko
  */
case class Card(
                 id: Option[Long],
                 word: String,
                 translation: String,
                 cardSetId: Long
               )
