package com.example.expencetracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.expencetracker.ui.theme.Zinc

@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (nameRow, list, card, topBar) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.ic_topbar),
                contentDescription = null,
                modifier = Modifier.constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 64.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
                    .constrainAs(nameRow) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Column {
                    Text(
                        text = "Good Afternoon",
                        fontSize = 16.sp,
                        color = Color.White
                    )

                    Text(
                        text = "CodeWithMe",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.c_notification),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }

            CardItem(
                modifier = Modifier.constrainAs(card) {
                    top.linkTo(parent.top, margin = 100.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            TransectionList(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(list) {
                        top.linkTo(card.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                    }
            )
        }
    }
}

@Composable
fun CardItem(modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Zinc)
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Column(
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Text(
                    text = "Total Balance",
                    fontSize = 16.sp,
                    color = Color.White
                )

                Text(
                    text = "$ 5000",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Image(
                painter = painterResource(id = R.drawable.dots_menu),
                contentDescription = null,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            CardRowItem(
                modifier = Modifier.align(Alignment.CenterStart),
                title = "Income",
                amount = "$ 3,297.78",
                image = R.drawable.ic_income
            )

            CardRowItem(
                modifier = Modifier.align(Alignment.CenterEnd),
                title = "Expense",
                amount = "$ 284.00",
                image = R.drawable.ic_expense
            )
        }
    }
}

@Composable
fun TransectionList(modifier: Modifier) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Recent Transactions",
                fontSize = 20.sp
            )

            Text(
                text = "See All",
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }

        TrasectionItem(
            title = "Netflix",
            amount = "+ $ 120.00",
            icon = R.drawable.ic_netflix,
            date = "Today",
            color = Color.Green
        )

        TrasectionItem(
            title = "Upwork",
            amount = "- $ 167.00",
            icon = R.drawable.ic_upwork,
            date = "Today",
            color = Color.Red
        )

        TrasectionItem(
            title = "Paypal",
            amount = "+ $ 200.00",
            icon = R.drawable.ic_paypal,
            date = "Today",
            color = Color.Green
        )

        TrasectionItem(
            title = "Starbucks",
            amount = "- $ 85.00",
            icon = R.drawable.ic_starbucks,
            date = "Today",
            color = Color.Red
        )
    }
}

@Composable
fun CardRowItem(
    modifier: Modifier,
    title: String,
    amount: String,
    image: Int
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null
            )

            Spacer(
                modifier = Modifier.size(8.dp)
            )

            Text(
                text = title,
                fontSize = 16.sp,
                color = Color.White
            )
        }

        Text(
            text = amount,
            fontSize = 20.sp,
            color = Color.White
        )
    }
}

@Composable
fun TrasectionItem(
    title: String,
    amount: String,
    icon: Int,
    date: String,
    color: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )

        Spacer(
            modifier = Modifier.size(8.dp)
        )

        Column {
            Text(
                text = title,
                fontSize = 16.sp
            )

            Text(
                text = date,
                fontSize = 12.sp
            )
        }

        Spacer(
            modifier = Modifier.weight(1f)
        )

        Text(
            text = amount,
            fontSize = 20.sp,
            color = color
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}