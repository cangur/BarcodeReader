package org.kodluyoruz.barcodereader

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.SparseArray
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.vision.barcode.Barcode
import info.androidhive.barcode.BarcodeReader

class ScanActivity : AppCompatActivity(), BarcodeReader.BarcodeReaderListener {

    private lateinit var barcodeReader: BarcodeReader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        barcodeReader = supportFragmentManager.findFragmentById(R.id.barcode_scanner) as BarcodeReader
    }

    override fun onBitmapScanned(sparseArray: SparseArray<Barcode>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onScannedMultiple(barcodes: MutableList<Barcode>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCameraPermissionDenied() {
        finish()
    }

    override fun onScanned(barcode: Barcode?) {
        barcodeReader.playBeep()

        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("code", barcode?.displayValue)
        startActivity(intent)
    }

    override fun onScanError(errorMessage: String?) {
        Toast.makeText(applicationContext, "Error occurred while scanning: $errorMessage", Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.getItemId() == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


}
