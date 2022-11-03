package com.shcherbakov_bogdan.myclip.ui.ownView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.shcherbakov_bogdan.myclip.R
import com.shcherbakov_bogdan.myclip.utils.pack

class BubbleGraph : View {

    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.BubbleGraph)
        circleFill?.color =
            typedArray.getColor(R.styleable.BubbleGraph_circle_fill_color, Color.BLUE)
        circleStroke?.color =
            typedArray.getColor(R.styleable.BubbleGraph_circle_border_color, Color.BLUE)
        typedArray.recycle()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    private var circleFill: Paint? = null
    private var circleStroke: Paint? = null
    private var args: List<Double> = listOf(10.0, 10.0, 10.0)
    private var cx: Float = 0f
    private var cy: Float = 0f
    private var strokeWidth: Int = 10
    private var minVSize: Int = 0

    init {
        circleStroke = Paint()
        circleStroke?.color = Color.RED
        circleStroke?.flags = Paint.ANTI_ALIAS_FLAG
        circleFill = Paint()
        circleFill?.color = Color.GREEN
        circleFill?.flags = Paint.ANTI_ALIAS_FLAG
    }

    override fun onDraw(canvas: Canvas?) {
        setMeasuredDimension(cx.toInt(), cy.toInt())
        args = listOf(
            150.0, 50.0, 70.0, 120.0
        )
        val floatList = pack(args)
        for (args in floatList) {
            canvas?.drawCircle(
                args.first.toFloat() + cx,
                args.second.toFloat() + cy,
                args.third.toFloat(),
                circleStroke!!
            )
        }
        for (args in floatList) {
            canvas?.drawCircle(
                args.first.toFloat() + cx,
                args.second.toFloat() + cy,
                args.third.toFloat() - strokeWidth,
                circleFill!!
            )
        }


        super.onDraw(canvas)
    }

    private fun decodeMeasureSpec(measureSpec: Int): Int {
        val result: Int
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        result = if (specMode == MeasureSpec.UNSPECIFIED) 350 else specSize
        return result
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val mWidth = decodeMeasureSpec(widthMeasureSpec) //Достаём размер View
        val mHeight = decodeMeasureSpec(heightMeasureSpec)

        minVSize =
            mWidth.coerceAtMost(mHeight) //Вычисляем минимальный размер (будем рисовать круги по минимальному размеру View в высоте или ширине)

        setMeasuredDimension(mWidth, mHeight) //Сохраненяем измеренную ширину и высоту для View

        cx = mWidth * 0.5f //Делим пополам высоту и ширину View
        cy = mHeight * 0.5f
    }

}