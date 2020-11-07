package www.runa.puebaiii;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class GuardarPuntos extends FragmentActivity implements OnMapReadyCallback {

    Button btnVerPuntos;
    GoogleMap map;
    SupportMapFragment mapFragment;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar_puntos);

        btnVerPuntos = (Button) findViewById(R.id.btnGenerarPuntos);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                String sitio = searchView.getQuery().toString();
                List<Address> SitioAricaList = null;
                if (sitio != null || !sitio.equals("")) {
                    Geocoder geocoder = new Geocoder(GuardarPuntos.this);
                    try {
                        SitioAricaList = geocoder.getFromLocationName(sitio, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address sitioArica = SitioAricaList.get(0);
                    LatLng latLng = new LatLng(sitioArica.getLatitude(), sitioArica.getLongitude());
                    map.addMarker(new MarkerOptions().position(latLng).title(sitio));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}