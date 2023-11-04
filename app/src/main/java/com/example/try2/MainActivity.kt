package com.example.try2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.try2.ui.theme.Try2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Try2Theme {
                // A surface container using the 'background' color from the theme
                MyApp()
            }
        }
    }
}




data class NavItemState(
    val title : String,
    val SelectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val hasBadge : Boolean,
    var badgeNum : Int

)



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(modifier: Modifier = Modifier){
    val items =listOf(
        NavItemState(
            "Home",
            Icons.Filled.Home,
            Icons.Filled.Home,
            false,
            0
        ),       NavItemState(
            "Inbox",
            Icons.Filled.Email,
            Icons.Filled.Email,
            false,
            10
        ),
        NavItemState(
            "Account",
            Icons.Filled.Face,
            Icons.Filled.Face,
            true,
            10
        ),



        )

    var bottomNavState by rememberSaveable{
        mutableStateOf(0 )
    }
    Scaffold(


        topBar = {
                 TopAppBar( title = {
                     Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                         Text("Top App Bar" )
                     }

                 },
                     modifier.padding(10.dp).clip( RoundedCornerShape(20)),
                     navigationIcon = {
                         IconButton(onClick = { /*TODO*/ }) {
                             Icon(imageVector = Icons.Default.Menu, contentDescription = " Menu icon")
                             
                         }
                     },

                     actions = {
                         IconButton(onClick = { /*TODO*/ }) {
                             BadgedBox( badge = {
                                 Badge(modifier.size(10.dp)){}

                             }

                             ) {

                                 Icon(
                                     imageVector = Icons.Outlined.FavoriteBorder,
                                     contentDescription = " Favorite icon"
                                 )
                             }

                         }
                         IconButton(onClick = { /*TODO*/ }) {
                             Icon(imageVector = Icons.Outlined.ShoppingCart, contentDescription = " ShoppingCart icon")

                         }
                     },
                     colors = TopAppBarDefaults.smallTopAppBarColors(
                          containerColor = Color(0xFFE0A9A5)
                     )


                 )

        },


        bottomBar = {

            NavigationBar(
                modifier
                    .padding(15.dp)
                    .clip(RoundedCornerShape(20.dp)),
                containerColor = Color(0xFFE0A9A5)


            ) {
                items.forEachIndexed{index, item ->



                    NavigationBarItem(selected =  bottomNavState == index,
                        onClick = { bottomNavState = index
                            if(item.badgeNum>0) item.badgeNum -= 1


                                  },
                        icon = {
                            BadgedBox(badge = {
                                if(item.hasBadge) Badge{}
                                if ( item.badgeNum != 0) Badge {
                                    Text(text = item.badgeNum.toString())
                                }
                            })
                            {
                                Icon( imageVector =  if ( bottomNavState == index)  item.SelectedIcon else item.unSelectedIcon,
                                    contentDescription = item.title


                                )


                            }


                        }
                        ,
                        label={
                            Text( text =item.title )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor =  Color (0xFF552A27),
                            selectedTextColor = Color( 0xFF63332F),
                            indicatorColor = Color(0xFFBB7E7A)
                        )
                    )
                }
            }
        }


    ) {contentPadding->
        Column (
            modifier
                .padding(contentPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = items[bottomNavState].title, fontWeight = FontWeight.ExtraBold, fontSize = 44.sp
            )

        }


    }

}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Try2Theme {
        MyApp()
    }
}