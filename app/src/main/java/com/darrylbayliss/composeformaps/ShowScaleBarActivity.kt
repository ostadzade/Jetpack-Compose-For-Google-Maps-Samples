package com.darrylbayliss.composeformaps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.clustering.Clustering
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.widgets.DisappearingScaleBar

@OptIn(MapsComposeExperimentalApi::class)
class ShowScaleBarActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val hydePark = LatLng(51.508610, -0.163611)
            val regentsPark = LatLng(51.531143, -0.159893)
            val primroseHill = LatLng(51.539556, -0.16076088)

            val crystalPalacePark = LatLng(51.42153, -0.05749)
            val greenwichPark = LatLng(51.476688, 0.000130)
            val lloydPark = LatLng(51.364188, -0.080703)
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(hydePark, 10f)
            }

            val parkMarkers = remember {
                mutableStateListOf(
                    ParkItem(
                        hydePark,
                        "Hyde Park",
                        "Marker in hyde Park"
                    ),
                    ParkItem(
                        regentsPark,
                        "Regents Park",
                        "Marker in Regents Park"
                    ),
                    ParkItem(
                        primroseHill,
                        "Primrose Hill",
                        "Marker in Primrose Hill"
                    ),
                    ParkItem(
                        crystalPalacePark,
                        "Crystal Palace",
                        "Marker in Crystal Palace"
                    ),
                    ParkItem(
                        greenwichPark,
                        "Greenwich Park",
                        "Marker in Greenwich Park"
                    ),
                    ParkItem(
                        lloydPark,
                        "Lloyd park",
                        "Marker in Lloyd Park"
                    ),
                )
            }

            Box(contentAlignment = Alignment.Center) {

                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState
                ) {
                    Clustering(items = parkMarkers,
                        onClusterClick = {
                            // Handle when the cluster is clicked
                            true
                        }, onClusterItemClick = { marker ->
                            // Handle when a marker in the cluster is clicked
                            true
                        })
                }

                // You can also use the ScaleBar composable
                DisappearingScaleBar(
                    modifier = Modifier
                        .padding(top = 5.dp, end = 15.dp)
                        .align(Alignment.TopStart),
                    cameraPositionState = cameraPositionState
                )
            }
        }
    }

    data class ParkItem(
        val itemPosition: LatLng,
        val itemTitle: String,
        val itemSnippet: String
    ) : ClusterItem {
        override fun getPosition(): LatLng =
            itemPosition

        override fun getTitle(): String =
            itemTitle

        override fun getSnippet(): String =
            itemSnippet

        override fun getZIndex(): Float? = null
    }
}
