package www.runa.puebaiii;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;


public class MapsActivity extends AppCompatActivity implements GoogleMap.OnMarkerDragListener,OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private Marker MisMarcadores,MarcadorMovimiento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;




        //IP-CFT_SANTO-TOMAS
        LatLng arica_IPCFT_SANTO_TOMAS = new LatLng(-18.483446081815273,-70.3103825426815);
        mMap.addMarker(new MarkerOptions().position(arica_IPCFT_SANTO_TOMAS).title
                ("Universidad Santo Tomás, Chiloé, 100 1110 Arica, Chile"));
/*
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.))*/
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(arica_IPCFT_SANTO_TOMAS,13));

        //UNIVERSIDAD DE TARAPACA
        LatLng arica_UTA = new LatLng(-18.487561055741665,-70.29503674381853);
        mMap.addMarker(new MarkerOptions().position(arica_UTA).title
                ("Universidad de Tarapacá, Paipote, Arica, Chile"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(arica_UTA,13));

        //INCAP

        LatLng arica_INACAP = new LatLng(-18.469059986122502,-70.30298184458123);
        mMap.addMarker(new MarkerOptions().position(arica_INACAP).title
                ("Inacap, Avenida Santa María 2190, 102 0759 Arica, Chile"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(arica_INACAP,13));


        ///MARCADOR LIBRE
        LatLng MarcadorLibre = new LatLng(-18.4785288,-70.3211394);
        MarcadorMovimiento = googleMap.addMarker(new MarkerOptions()
                .position(MarcadorLibre).title("Marcador Libre").draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


        ///EVENTOS DE MIS MARCADORES
        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnMarkerDragListener(this);

    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        ///MOSTRAR LATITUD Y LONGITUD AL SELECIONAR PUNTO DE COORDENADA
        if (marker.equals(MisMarcadores)) {
            String Lat,Lng;
            Lat = Double.toString(marker.getPosition().latitude);
            Lng = Double.toString(marker.getPosition().longitude);
            Toast.makeText(this,Lat + ","+Lng,Toast.LENGTH_LONG).show();
        }




        return false;
    }

    ///MARCADOR LIBRE

    @Override
    public void onMarkerDragStart(Marker marker) {
        if (marker.equals(MisMarcadores)){
            Toast.makeText(this,"Iniciemos el vuelo",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onMarkerDrag(Marker marker) {
        if (marker.equals(MisMarcadores)) {
            String Titulo = String.format(Locale.getDefault(),getString(R.string.marker_detail_latlng),
                    marker.getPosition().latitude,
                    marker.getPosition().longitude);
            setTitle(Titulo);

        }

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        if (marker.equals(MisMarcadores)){
            Toast.makeText(this,"Entonces aquí nos quedamos uff que lejos",Toast.LENGTH_LONG).show();
            setTitle(R.string.app_name);
        }

    }
}