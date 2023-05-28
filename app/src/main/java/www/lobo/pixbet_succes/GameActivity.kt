package www.lobo.pixbet_succes

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {

    private val imageIds = intArrayOf(
        R.drawable.open_1, R.drawable.open_2, R.drawable.open_3, R.drawable.open_4,
        R.drawable.open_5, R.drawable.open_6, R.drawable.open_7, R.drawable.open_8
    )
    private lateinit var openedImageViews: MutableList<ImageView>
    private var numImagesOpened: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val imageView = findViewById<ImageView>(R.id.exit)
        imageView.setOnClickListener {
            finish()
        }


        openedImageViews = mutableListOf()

        val imageViews = arrayOf(
            findViewById<ImageView>(R.id.plate_1), findViewById(R.id.plate_2), findViewById(R.id.plate_3), findViewById(R.id.plate_4),
            findViewById(R.id.plate_5), findViewById(R.id.plate_6), findViewById(R.id.plate_7), findViewById(R.id.plate_8),
            findViewById(R.id.plate_9), findViewById(R.id.plate_10), findViewById(R.id.plate_11), findViewById(R.id.plate_12),
            findViewById(R.id.plate_13), findViewById(R.id.plate_14), findViewById(R.id.plate_15), findViewById(R.id.plate_16)
        )

        val randomImageIds = (imageIds.toList() + imageIds.toList()).shuffled()

        for (i in imageViews.indices) {
            val imageView = imageViews[i]
            imageView.setImageResource(R.drawable.close)
            imageView.tag = randomImageIds[i]
            imageView.setOnClickListener { onImageClicked(it) }
        }
    }

    private fun onImageClicked(view: View) {
        val imageView = view as ImageView
        val imageId = imageView.tag as Int

        if (!openedImageViews.contains(imageView) && openedImageViews.size < 2) {
            imageView.setImageResource(imageId)
            openedImageViews.add(imageView)

            if (openedImageViews.size == 2) {
                val image1 = openedImageViews[0]
                val image2 = openedImageViews[1]

                if (image1.tag == image2.tag) {
                    numImagesOpened += 2

                    openedImageViews.remove(image1)
                    openedImageViews.remove(image2)

                    image1.setOnClickListener { onImageClicked(it) }
                    image2.setOnClickListener { onImageClicked(it) }


                    if (numImagesOpened == imageIds.size) {
                        // Congratulations, the game is finished
                        restartGame()
                    }
                } else {
                    // Close the images after a delay
                    imageView.postDelayed({
                        image1.setImageResource(R.drawable.close)
                        image2.setImageResource(R.drawable.close)
                        openedImageViews.remove(image1)
                        openedImageViews.remove(image2)

                        // Установить обработчики нажатий для image1 и image2
                        image1.setOnClickListener { onImageClicked(it) }
                        image2.setOnClickListener { onImageClicked(it) }
                    }, 1000)
                }
            }
        }
    }
    private fun restartGame() {
        numImagesOpened = 0
        openedImageViews.clear()

        val imageViews = arrayOf(
            findViewById<ImageView>(R.id.plate_1), findViewById(R.id.plate_2), findViewById(R.id.plate_3), findViewById(R.id.plate_4),
            findViewById(R.id.plate_5), findViewById(R.id.plate_6), findViewById(R.id.plate_7), findViewById(R.id.plate_8),
            findViewById(R.id.plate_9), findViewById(R.id.plate_10), findViewById(R.id.plate_11), findViewById(R.id.plate_12),
            findViewById(R.id.plate_13), findViewById(R.id.plate_14), findViewById(R.id.plate_15), findViewById(R.id.plate_16)
        )

        val randomImageIds = imageIds.toList().shuffled()

        for (i in imageViews.indices) {
            val imageView = imageViews[i]
            imageView.setImageResource(R.drawable.close)
            imageView.tag = randomImageIds[i]
            imageView.setOnClickListener { onImageClicked(it) } // Установка обработчика нажатия
        }
    }
}