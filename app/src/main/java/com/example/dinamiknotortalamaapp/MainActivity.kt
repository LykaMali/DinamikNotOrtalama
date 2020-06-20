package com.example.dinamiknotortalamaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.yeni_ders_layout.view.*
import kotlinx.android.synthetic.main.yeni_ders_layout.view.etYeniDersAdi

class MainActivity : AppCompatActivity() {

    private val DERSLER= arrayOf("Matematik", "Türkçe", "Fizik", "Algoritma", "Tarih")
    private var tumDerslerinBilgileri:ArrayList<Dersler> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, DERSLER)
         etDersAdi.setAdapter(adapter)

        if (rootLayout.childCount == 0) {
            btnOrtHesapla.visibility = View.INVISIBLE
        } else btnOrtHesapla.visibility = View.VISIBLE

        btnDersEkle.setOnClickListener {

            if (!etDersAdi.text.isNullOrEmpty()) {
                var inflater = LayoutInflater.from(this)

                var yeniDersView = inflater.inflate(R.layout.yeni_ders_layout, null)

                yeniDersView.etYeniDersAdi.setAdapter(adapter)

                var dersAdi = etDersAdi.text.toString()
                var dersKredi = spnDersKredi.selectedItem.toString()
                var dersHarf = spnDersNot.selectedItem.toString()

                yeniDersView.etYeniDersAdi.setText(dersAdi)
                yeniDersView.spnYeniDersKredi.setSelection(
                    spinnerDegeriIndexiniBul(
                        spnDersKredi,
                        dersKredi
                    )
                )
                yeniDersView.spnYeniDersNot.setSelection(
                    spinnerDegeriIndexiniBul(
                        spnDersNot,
                        dersHarf
                    )
                )


                yeniDersView.btnDersSil.setOnClickListener {
                    rootLayout.removeView(yeniDersView)
                    if (rootLayout.childCount == 0) {
                        btnOrtHesapla.visibility = View.INVISIBLE
                    } else btnOrtHesapla.visibility = View.VISIBLE
                }




                rootLayout.addView(yeniDersView)

                btnOrtHesapla.visibility = View.VISIBLE

                sifirla()

            }else{
                FancyToast.makeText(this,"Ders Adını Giriniz !",FancyToast.LENGTH_LONG,FancyToast.INFO,true).show()
            }

    }

}

    fun sifirla(){
        etDersAdi.setText("")
        spnDersKredi.setSelection(0)
        spnDersNot.setSelection(0)
    }



    fun spinnerDegeriIndexiniBul(spinner: Spinner, aranacakDeger: String) : Int {

        var index = 0
        for (i in 0..spinner.count) {
            if (spinner.getItemAtPosition(i).toString().equals(aranacakDeger)) {
                index = i
                break
            }
        }
        return index
    }


    fun ortalamaHesapla(view: View) {
        var toplamNot = 0.0
        var toplamKredi = 0.0

        for (i in 0..rootLayout.childCount - 1){
            var tekSatir = rootLayout.getChildAt(i)

            var geciciDers = Dersler(tekSatir.etYeniDersAdi.text.toString(),
                ((tekSatir.spnYeniDersKredi.selectedItemPosition)+1).toString(),
                tekSatir.spnYeniDersNot.selectedItem.toString())

            tumDerslerinBilgileri.add(geciciDers)
        }
        for (oankiDers in tumDerslerinBilgileri){

            toplamNot +=harfiNotaCevir(oankiDers.dersHarfNot) * (oankiDers.dersKredisi.toDouble())
            toplamKredi += oankiDers.dersKredisi.toDouble()

        }
        FancyToast.makeText(this,"Ortalama : "+(toplamNot/toplamKredi),FancyToast.LENGTH_LONG,FancyToast.INFO,true).show()
        tumDerslerinBilgileri.clear()

    }

    fun harfiNotaCevir(gelenNotHarfDegeri: String) : Double{

        var deger = 0.0
        when(gelenNotHarfDegeri){
            "AA" -> deger =4.0
            "BA" -> deger =3.5
            "BB" -> deger =3.0
            "CB" -> deger =2.5
            "CC" -> deger =2.0
            "DC" -> deger =1.5
            "DD" -> deger =1.0
            "FF" -> deger =0.0
        }
        return deger

    }

}