package com.ozhan.mustafa.ann

/**
 * Created by Mustafa Özhan on 5/12/17 at 7:51 PM at 10:11 PM at 2:08 PM.
 */

internal class FuzzyNeuron(position: Double, angel: Double) {
    private var position: Double = 0.toDouble()
    private var angel: Double = 0.toDouble()

    private var positionLeft: Double = 0.toDouble()
    private var positionMiddle: Double = 0.toDouble()
    private var positionRight: Double = 0.toDouble()
    private var angelEast: Double = 0.toDouble()
    private var angelNorth: Double = 0.toDouble()
    private var angelWest: Double = 0.toDouble()
    var iteration = 0
        private set


    var output: Double = 0.toDouble()
        private set(output) = if (output > 15)
            field = 15.0
        else if (output <= -15)
            field = -15.0
        else
            field = output


    init {
        this.position = position
        this.angel = angel
    }

    fun calculateOutput() {


        iteration++

        if (position <= -3) {
            positionLeft = 1.0
            positionMiddle = 0.0
            positionRight = 0.0


        } else if (position > -3 && position < 0) {
            positionLeft = -position / 3
            positionMiddle = (3 - -position) / 3
            positionRight = 0.0


        } else if (position == 0.0) {
            positionMiddle = 1.0
            positionRight = 0.0
            positionLeft = 0.0

        } else if (position > 0 && position < 3) {

            positionLeft = 0.0
            positionRight = position / 3
            positionMiddle = (3 - position) / 3
        } else {
            positionMiddle = 0.0
            positionRight = 1.0
            positionLeft = 0.0
        }


        if (angel <= 0) {
            angelEast = 1.0
            angelNorth = 0.0
            angelWest = 0.0
        } else if (angel > 0 && angel < 90) {
            angelEast = (90 - angel) / 90
            angelNorth = angel / 90
            angelWest = 0.0


        } else if (angel == 90.0) {
            angelNorth = 1.0
            angelEast = 0.0
            angelWest = 0.0

        } else if (angel > 90 && angel < 180) {

            angelWest = (angel - 90) / 90
            angelNorth = (180 - angel) / 90
            angelEast = 0.0
        } else {
            angelNorth = 0.0
            angelEast = 0.0
            angelWest = 1.0
        }


        val weight: Double
        if (position <= -3) {
            weight = 15 * Math.min(positionLeft, angelNorth) + 15 * Math.min(angelWest, positionLeft)
            output = weight / (positionLeft + angelEast + angelNorth + angelWest)


        } else if (position > -3 && position < 0) {
            weight = -15 * Math.min(positionMiddle, angelEast) + 15 * (Math.min(angelNorth, positionLeft) +
                    Math.min(angelWest, positionLeft) +
                    Math.min(angelWest, positionMiddle))
            output = weight / (angelEast + angelNorth + angelWest + positionLeft + positionMiddle)


        } else if (position == 0.0) {
            weight = -15 * Math.min(positionMiddle, angelEast) + 15 * Math.min(angelWest, positionMiddle)
            output = weight / (positionMiddle + angelEast + angelNorth + angelWest)


        } else if (position > 0 && position < 3) {
            weight = 15 * Math.min(angelWest, positionMiddle) + -15 * (Math.min(positionMiddle, angelEast)
                    + Math.min(positionRight, angelEast)
                    + Math.min(angelNorth, positionRight))
            output = weight / (positionMiddle + positionRight + angelEast + angelNorth + angelWest)


        } else {
            weight = -15 * (Math.min(positionRight, angelEast) + Math.min(angelNorth, positionRight))
            output = weight / (positionRight + angelEast + angelNorth + angelWest)

        }


        if (output > 0.5 || output < -0.5) {

            position += 0.03 * output


            if (angel < 90)
                angel += 0.03 * Math.abs(output)
            else
                angel -= 0.03 * Math.abs(output)

            calculateOutput()
        }

    }

}
