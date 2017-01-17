//package model.domain;
//
//import java.time.LocalDateTime;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//
//@Entity
//public class CardAnswer {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long id;
//
//    private LocalDateTime time;
//
//    private Boolean correct;
//
//    @ManyToOne
//    @JoinColumn(name = "answer_type_id")
//    private AnswerType answerType;
//
//    @ManyToOne
//    @JoinColumn(name = "card_id")
//    private Card card;
//
//    public Long getId() {
//        return id;
//    }
//
//    public CardAnswer setId(Long id) {
//        this.id = id;
//        return this;
//    }
//
//    public LocalDateTime getTime() {
//        return time;
//    }
//
//    public CardAnswer setTime(LocalDateTime time) {
//        this.time = time;
//        return this;
//    }
//
//    public Boolean getCorrect() {
//        return correct;
//    }
//
//    public CardAnswer setCorrect(Boolean correct) {
//        this.correct = correct;
//        return this;
//    }
//
//    public AnswerType getAnswerType() {
//        return answerType;
//    }
//
//    public CardAnswer setAnswerType(AnswerType answerType) {
//        this.answerType = answerType;
//        return this;
//    }
//
//    public Card getCard() {
//        return card;
//    }
//
//    public CardAnswer setCard(Card card) {
//        this.card = card;
//        return this;
//    }
//}
