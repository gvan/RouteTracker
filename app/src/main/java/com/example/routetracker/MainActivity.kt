package com.example.routetracker

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
//        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        with(googleMap.uiSettings) {
            isZoomControlsEnabled = true
            isZoomGesturesEnabled = true
        }
        
        val line = "uq|rHcyhyDBGiK{OmAwBIKOGm@NiEv@gBNwACeAWk@[u@g@q@Mi@H_Af@g@\\aCnCuC|DsDpGWEYU}BkE?QNq@lCc@bAa@bAq@bAmA|@gBfJ}R`BaEpDaMlBkHR{@pJc]^a@nCiIjAiD`AmChHqQlE{K|D{JrAiD|@qBzBaEhEwGrFmIlCoDdF_GzB}BdHcG\\OjR{QnBcAlIcDjJcD|MwD|Ai@tKoChAU^AtCi@tDy@r@@f@Rt@v@NVr@vC^vBDj@@rAM`BAl@eAdIOpD@lCJpBTfBt@zE`CjNlB~K~@fEh@bChB`GjQvb@R~@zAvDzGrObC`GxFnOrBnG`C~IxBbKdArFfD~NvDhN`CfHdEdLtIjSjBdDnBjCnBvBrBdBnCdBhLjGR@tBpBnD~DnEvF`IbKrCzCrAlAtAz@r@XpKtGhBxA~BdCrAjBtA`C~AjDn@bBjCbJnw@zmCPbAlB`H^zA@h@rC`KtBfHrC~HlAxCdBjDhCzEhGxKjKrQlFlJ|AbC`C`EfGdLvNfWrLxSxQf[zF|JfQhZ|LjSdKfQrm@pdArAtBrJrPlXtd@vZ|g@fLxRfNtUpC`F~F|IrD`FfEdFfAjAzHhH`BpAnEzCxIvG`NjKhIrFb\\tUzPzLzDtCtFxEhFpFpBxB`I|Jz@hAlDhF`ElHpEtI~EhJxI|PdIfO`h@h}@vArBjBrBlFvEva@j]lIvGlGhDjJnEt{@z`@vXnMdAp@`BvAn@p@lBjCp@nAvAfDx@rCp@~C^bCNxALtBJhCDlED``@FxKLpFT`Ef@rFh@~Dv@vDbB`GxAbEpA~CnFtL~@dCrNhe@zAfFhDjMVt@ne@lhB`CnHdA`Cb@x@tAfBhD~CtA|@vYlPpHlElDlBrXxOdEfCdBhAtEpDvDjD~I|J~e@dl@vA~Aza@jg@hA~AbCpCfGpH`w@n`AtKnMlJfLlBxB|NrQhP~RjExFlMlOxJxLpEhFfFdF`CnBnCpBpC~AzAr@jC~@nDp@~CR|CFpBE|Fe@bAI|Cg@tFsA~LyDjBq@fEqAvEeBlJqC|DsArHwBbi@gQ|DkBlDkBtGoEtE}DnHmH`BgBpAiBpCaDjDeEvL_N`CuBzAoA~B_BtG_DrBs@zCm@bAKjAEtCAjCLxEn@hJpClJhDxOhGzBhA~B`BtHxG~B`BdBz@xBp@|Bb@tAH|B?vO_@L`SWZUHWCd@xAZbBL~@RlGRrD^zDd@nDl@lDfA~EbAlDlIxVzQbj@xWpw@`G~Q|Mr`@zjBnuFzaAnwC`Wru@|IhXbm@hhBjG`RLXNZxG|RzCbJx@vB|GjOfAtCtBhHf@xBz@jE~_@vyBzDtT~AhIbAvDh@dB|F~ObJjVtA`ER~@PhBBjBS|RKbI]jRWhFa@fDgApIcEv]q@bGcApIQ|BErBDfRDt`@Lnt@NnmBAlIO~Fe@hH{Bv[O~C[dJe@dMQrBe@zCia@loBqGfZq@hCGTOPE^D\\NTT@DCPNbATj@n@lCjCvJbJlLxKjTdRxKdN~LhObCzD`ArA\\n@bE~IjAhCVx@|@jFtIjk@ad@`VYXa@v@a@fAWrAcA|R?r@J\\l@dAh@nA?hBFxC@dBI|BI`@IPg@b@kBFeDMuDc@_B?[FQHYb@e@dAg@|AsAxFgAjFoAxGO\\_@PoHqHyA_Bi@c@}CeBoBfVcBxRP|@XlD`@rBh@~A"
        val points =  PolyUtil.decode(line)
        print("APPLOGS points size ${points.size}")
        val polylineOptions = PolylineOptions().apply {
            addAll(points)
            color(Color.BLUE)
            width(16f)
        }
        googleMap.addPolyline(polylineOptions)
    }
}