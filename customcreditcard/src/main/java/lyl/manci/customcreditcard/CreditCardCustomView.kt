package lyl.manci.customcreditcard

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.view_credit_card.view.*
import kotlinx.android.synthetic.main.view_credit_card_back.view.*
import kotlinx.android.synthetic.main.view_credit_card_front.view.*


/**       Code with ❤  ´• ل •`   ❤
▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬
▬     Created by Leyla Akmancı                ▬
▬     ▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬    ▬
▬     leyla.manci@gmail.com                           ▬
▬     ▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬     ▬
▬     10/08/2020 - 19:05        ▬
▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬
 */
class CreditCardCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(
    context,
    attrs,
    defStyleAttr
) {
    companion object {
        private const val CARD_NUMBER_TOTAL_SYMBOLS = 19 // size of pattern 0000-0000-0000-0000
        private const val CARD_NUMBER_TOTAL_DIGITS =
            16 // max numbers of digits in pattern: 0000 x 4
        private const val CARD_NUMBER_DIVIDER_MODULO =
            5 // means divider position is every 5th symbol beginning with 1
        private const val CARD_NUMBER_DIVIDER_POSITION =
            CARD_NUMBER_DIVIDER_MODULO - 1 // means divider position is every 4th symbol beginning with 0
        private const val CARD_NUMBER_DIVIDER = ' '

        private const val CARD_DATE_TOTAL_SYMBOLS = 5 // size of pattern MM/YY
        private const val CARD_DATE_TOTAL_DIGITS = 4 // max numbers of digits in pattern: MM + YY
        private const val CARD_DATE_DIVIDER_MODULO =
            3 // means divider position is every 3rd symbol beginning with 1
        private const val CARD_DATE_DIVIDER_POSITION =
            CARD_DATE_DIVIDER_MODULO - 1 // means divider position is every 2nd symbol beginning with 0
        private const val CARD_DATE_DIVIDER = '/'
        private const val CARD_CVC_TOTAL_SYMBOLS = 3
        private const val DEFAULT_VISIBLE_DURATION: Long = 1000
        private const val DEFAULT_INVISIBLE_DURATION: Long = 700
        private const val DEFAULT_VISIBLE_ROTATION = 10F
    }

    private var visibleDuration = DEFAULT_VISIBLE_DURATION
    private var invisibleDuration = DEFAULT_INVISIBLE_DURATION
    private var visibleRotation = DEFAULT_VISIBLE_ROTATION


    init {

        LayoutInflater.from(context).inflate(R.layout.view_credit_card, this)
        setupAttributes(attrs)

        btnCvc.setOnClickListener {
            incCreditCardFront.animate().rotation(visibleRotation).translationX(-1000F)
                .alpha(0.0F).duration = invisibleDuration
            incCreditCardBack.animate().translationY(0F).alpha(1.0F).duration = visibleDuration
        }

        btnFront.setOnClickListener {
            incCreditCardFront.animate().rotation(0F).translationX(0F).alpha(1.0F).duration =
                visibleDuration
            incCreditCardBack.animate().translationY(0F).alpha(0.0F).duration = invisibleDuration
        }
        txtCardNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null) {
                    if (!isInputCorrect(
                            p0,
                            CARD_NUMBER_TOTAL_SYMBOLS,
                            CARD_NUMBER_DIVIDER_MODULO,
                            CARD_NUMBER_DIVIDER
                        )
                    ) {
                        p0.replace(
                            0,
                            p0.length,
                            concatString(
                                getDigitArray(p0, CARD_NUMBER_TOTAL_DIGITS),
                                CARD_NUMBER_DIVIDER_POSITION,
                                CARD_NUMBER_DIVIDER
                            )
                        )
                    }
                }

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        })
        txtCardExpire.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null) {
                    if (!isInputCorrect(
                            p0,
                            CARD_DATE_TOTAL_SYMBOLS,
                            CARD_DATE_DIVIDER_MODULO,
                            CARD_DATE_DIVIDER
                        )
                    ) {
                        p0.replace(
                            0,
                            p0.length,
                            concatString(
                                getDigitArray(p0, CARD_DATE_TOTAL_DIGITS),
                                CARD_DATE_DIVIDER_POSITION,
                                CARD_DATE_DIVIDER
                            )
                        )
                    }
                }

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        txtCardHolder.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                txtCardHolderBackSide.text = p0

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })


    }


    infix fun setupAttributes(attrs: AttributeSet?) {


        attrs?.let {

            // Obtain a typed array of attributes
            val typedArray = context.theme.obtainStyledAttributes(
                attrs, R.styleable.creditCardView_attrs,
                0, 0
            )

            // Extract custom attributes into member variables
            visibleRotation = typedArray.getInteger(
                R.styleable.creditCardView_attrs_visibleRotation,
                DEFAULT_VISIBLE_ROTATION.toInt()
            ).toFloat()


            visibleDuration = typedArray.getInteger(
                R.styleable.creditCardView_attrs_visibleDuration,
                DEFAULT_VISIBLE_DURATION.toInt()
            ).toLong()
            invisibleDuration = typedArray.getInteger(
                R.styleable.creditCardView_attrs_invisibleDuration,
                DEFAULT_INVISIBLE_DURATION.toInt()
            ).toLong()
            // TypedArray objects are shared and must be recycled.
            typedArray.recycle()

        }

    }
}