create database [Assignment1_DoTheDuong]
go
USE [Assignment1_DoTheDuong]
GO
/****** Object:  Table [dbo].[tblCategory]    Script Date: 20/3/2021 4:52:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCategory](
	[typeID] [varchar](10) NOT NULL,
	[typeName] [nvarchar](30) NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[typeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblFood]    Script Date: 20/3/2021 4:52:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblFood](
	[foodID] [varchar](20) NOT NULL,
	[foodName] [nvarchar](30) NULL,
	[img] [nvarchar](100) NULL,
	[amount] [int] NULL,
	[price] [float] NULL,
	[description] [nvarchar](300) NULL,
	[typeID] [varchar](10) NULL,
	[status] [bit] NULL,
	[createDate] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[foodID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrder]    Script Date: 20/3/2021 4:52:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrder](
	[orderID] [varchar](10) NOT NULL,
	[userID] [varchar](30) NULL,
	[totalPrice] [float] NULL,
	[status] [bit] NULL,
	[orderDate] [date] NULL,
	[addressDelivery] [nvarchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrderDetail]    Script Date: 20/3/2021 4:52:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrderDetail](
	[detailID] [varchar](10) NOT NULL,
	[orderID] [varchar](10) NULL,
	[foodID] [varchar](20) NULL,
	[quantity] [int] NULL,
	[price] [float] NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[detailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRecordUpdate]    Script Date: 20/3/2021 4:52:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRecordUpdate](
	[recordID] [varchar](30) NOT NULL,
	[userID] [varchar](30) NULL,
	[foodID] [varchar](20) NULL,
	[dateUpdate] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[recordID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRole]    Script Date: 20/3/2021 4:52:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRole](
	[roleId] [varchar](30) NOT NULL,
	[roleName] [nvarchar](30) NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[roleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUser]    Script Date: 20/3/2021 4:52:17 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUser](
	[userId] [varchar](30) NOT NULL,
	[fullName] [nvarchar](30) NULL,
	[password] [varchar](20) NULL,
	[roleID] [varchar](30) NULL,
	[status] [bit] NULL,
	[address] [nvarchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[tblCategory] ([typeID], [typeName], [status]) VALUES (N'CD', N'Candie', 1)
INSERT [dbo].[tblCategory] ([typeID], [typeName], [status]) VALUES (N'CFS', N'Coffee', 1)
INSERT [dbo].[tblCategory] ([typeID], [typeName], [status]) VALUES (N'CK', N'Cake', 1)
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0001', N'Cà phê đen đá', N'image/capheden.jpg', 40, 10000, N'Coffee provides a complex blend of different flavours, which together produce a range of sensory experiences', N'CFS', 1, CAST(N'2020-01-05' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0002', N'Cà phê sữa', N'image/caphesua.jpg', 30, 12000, N'Coffee provides a complex blend of different flavours, which together produce a range of sensory experiences', N'CFS', 1, CAST(N'2020-01-05' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0003', N'Capochino', N'image/capuchino.jpg', 36, 35000, N'Coffee provides a complex blend of different flavours, which together produce a range of sensory experiences', N'CFS', 1, CAST(N'2020-01-04' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0004', N'Bạc xỉu', N'image/bacxiu.jpg', 28, 15000, N'Coffee provides a complex blend of different flavours, which together produce a range of sensory experiences', N'CFS', 0, CAST(N'2020-01-03' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0008', N'Cupcake', N'image/cupcake.jpg', 20, 22000, N'Cake is a form of sweet food made from flour, sugar, and other ingredients, that is usually baked.', N'CK', 0, CAST(N'2019-12-24' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0009', N'Donut', N'image/donut.jpg', 9, 16000, N'Cake is a form of sweet food made from flour, sugar, and other ingredients, that is usually baked.', N'CK', 0, CAST(N'2019-11-30' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0010', N'Bánh cá tayaiki', N'image/banhcatayaiki.jpg', 15, 35000, N'Cake is a form of sweet food made from flour, sugar, and other ingredients, that is usually baked.', N'CK', 1, CAST(N'2021-01-09' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0011', N'Jawbreakers', N'image/Jawbreakers.jpg', 28, 5000, N'yummy', N'CD', 1, CAST(N'2020-12-06' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0012', N'Reeses peanut butter cups', N'image/Reeses-peanut-butter-cups.jpg', 23, 65000, N'yummy', N'CD', 1, CAST(N'2020-09-05' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0013', N'Hershey kisses', N'image/Hershey-kisses.jpg', 3, 25000, N'yummy', N'CD', 1, CAST(N'2021-01-05' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0014', N'Gummies', N'image/Gummies.jpg', 6, 15000, N'yummy', N'CD', 1, CAST(N'2021-01-06' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0015', N'Mentos', N'image/Mentos.jpg', 25, 6000, N'yummy', N'CD', 1, CAST(N'2021-01-07' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0016', N'Golden cake', N'image/Golden cake.jpg', 35, 150000, N'Cake is a form of sweet food made from flour, sugar, and other ingredients, that is usually baked.', N'CK', 1, CAST(N'2021-01-08' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0017', N'Birthday cake', N'image/Birthday.jpg', 62, 67000, N'Cake is a form of sweet food made from flour, sugar, and other ingredients, that is usually baked.', N'CK', 1, CAST(N'2021-01-09' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0018', N'Mochi', N'image/Mochi.jpg', 41, 35000, N'Cake is a form of sweet food made from flour, sugar, and other ingredients, that is usually baked.', N'CK', 1, CAST(N'2021-01-10' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0019', N'Mousse', N'image/Mousse.jpg', 58, 28000, N'Cake is a form of sweet food made from flour, sugar, and other ingredients, that is usually baked.', N'CK', 1, CAST(N'2021-01-12' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0020', N'Marshmallow', N'image/marshmallow.jpg', 28, 30000, N'yummy', N'CD', 0, CAST(N'2019-12-26' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0022', N'Caramel macchiato', N'image/caramel macchiato.jpg', 22, 48000, N'Coffee provides a complex blend of different flavours, which together produce a range of sensory experiences', N'CFS', 1, CAST(N'2020-01-11' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0023', N'Dark chocolate', N'image/dark chocolate.jpg', 60, 35000, N'Cake is a form of sweet food made from flour, sugar, and other ingredients, that is usually baked.', N'CK', 0, CAST(N'2020-01-05' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0024', N'Latte', N'image/latte.jpg', 50, 28000, N'Coffee provides a complex blend of different flavours, which together produce a range of sensory experiences', N'CFS', 0, CAST(N'2020-01-05' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0025', N'Lollipop', N'image/lollipop.jpg', 19, 15000, N'yummy', N'CD', 1, CAST(N'2020-01-08' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0026', N'Mocha', N'image/mocha.jpg', 35, 29000, N'Coffee provides a complex blend of different flavours, which together produce a range of sensory experiences', N'CFS', 0, CAST(N'2020-01-07' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0027', N'Strawberry', N'image/strawberry.jpg', 53, 35000, N'Cake is a form of sweet food made from flour, sugar, and other ingredients, that is usually baked.', N'CK', 1, CAST(N'2020-06-20' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0028', N'Brownie', N'image/brownie.jpg', 44, 26000, N'Cake is a form of sweet food made from flour, sugar, and other ingredients, that is usually baked.', N'CK', 1, CAST(N'2021-01-05' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0029', N'Cheesecake recipe', N'image/cheesecake recipe.jpg', 12, 30000, N'Cake is a form of sweet food made from flour, sugar, and other ingredients, that is usually baked.', N'CK', 1, CAST(N'2021-01-07' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0030', N'Macaron', N'image/macaron.jpg', 25, 15000, N'yummy', N'CD', 1, CAST(N'2021-01-08' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0031', N'Pudding', N'image/pudding.jpg', 35, 20000, N'Cake is a form of sweet food made from flour, sugar, and other ingredients, that is usually baked.', N'CK', 1, CAST(N'2021-01-07' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0032', N'Vanilla', N'image/vanilla.jpg', 60, 15000, N'Cake is a form of sweet food made from flour, sugar, and other ingredients, that is usually baked.', N'CK', 1, CAST(N'2021-01-01' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0033', N'Chocola matcha', N'image/Chocola-matcha.jpg', 50, 30000, N'Cake is a form of sweet food made from flour, sugar, and other ingredients, that is usually baked.', N'CK', 1, CAST(N'2021-01-10' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0034', N'Coffee Matcha', N'image/coffeeMatcha.jpg', 20, 21000, N'Coffee provides a complex blend of different flavours, which together produce a range of sensory experiences', N'CFS', 1, CAST(N'2021-01-12' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0035', N'Matcha cake', N'image/Matcha-cake.jpg', 30, 35000, N'Cake is a form of sweet food made from flour, sugar, and other ingredients, that is usually baked.', N'CK', 1, CAST(N'2021-01-12' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0036', N'Bánh bông lan', N'image/banhbonglan.jpg', 25, 23000, N'Cake is a form of sweet food made from flour, sugar, and other ingredients, that is usually baked.', N'CK', 1, CAST(N'2021-01-13' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0037', N'Dango', N'image/Dango.jpg', 69, 12000, N'yummy', N'CD', 1, CAST(N'2021-01-14' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0038', N'Higashi', N'image/Higashi.jpg', 5, 35000, N'yummy', N'CD', 1, CAST(N'2021-01-05' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0039', N'Yokan', N'image/Yokan.jpg', 9, 23000, N'Cake is a form of sweet food made from flour, sugar, and other ingredients, that is usually baked.', N'CK', 1, CAST(N'2021-01-06' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0040', N'Manju', N'image/Manju.jpg', 2, 16000, N'Cake is a form of sweet food made from flour, sugar, and other ingredients, that is usually baked.', N'CK', 1, CAST(N'2021-01-07' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0041', N'Monaka', N'image/Monaka.jpg', 7, 25000, N'Cake is a form of sweet food made from flour, sugar, and other ingredients, that is usually baked.', N'CK', 1, CAST(N'2021-01-08' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0042', N'Kitkat', N'image/Kitkat.jpg', 62, 7000, N'yummy', N'CD', 1, CAST(N'2021-01-13' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0043', N'Espresso', N'image/espresso.jpg', 54, 15000, N'Coffee provides a complex blend of different flavours, which together produce a range of sensory experiences', N'CFS', 1, CAST(N'2021-01-14' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0044', N'Doppio', N'image/Doppio.jpg', 25, 25000, N'Coffee provides a complex blend of different flavours, which together produce a range of sensory experiences', N'CFS', 1, CAST(N'2021-01-09' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0045', N'Red eye', N'image/red eye.jpg', 24, 36000, N'Coffee provides a complex blend of different flavours, which together produce a range of sensory experiences', N'CFS', 1, CAST(N'2021-01-06' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0046', N'Cortadito', N'image/Cortadito.jpg', 21, 25000, N'Coffee provides a complex blend of different flavours, which together produce a range of sensory experiences', N'CFS', 1, CAST(N'2021-01-05' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0047', N'Espresso cake', N'image/Espresso cake.jpg', 62, 16000, N'Coffee provides a complex blend of different flavours, which together produce a range of sensory experiences', N'CFS', 1, CAST(N'2021-01-04' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0050', N'Panna cotta', N'image/Panna cotta.jpg', 5, 15000, N'Cake is a form of sweet food made from flour, sugar, and other ingredients, that is usually baked.', N'CK', 1, CAST(N'2020-09-16' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0051', N'Tiramisu', N'image/Tiramisu.jpg', 15, 15000, N'Cake is a form of sweet food made from flour, sugar, and other ingredients, that is usually baked.', N'CK', 1, CAST(N'2021-01-08' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0052', N'Keomut', N'image/Keomut.jpg', 25, 30000, N'yummy', N'CD', 1, CAST(N'2021-01-10' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0111', N'Doublemint', N'image/doublemint.jpg', 1, 5000, N'yummy', N'CD', 1, CAST(N'2020-01-12' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'FC0999', N'Hard Candy', N'image/hard candy.jpg', 35, 8000, N'yummy', N'CD', 0, CAST(N'2019-01-06' AS Date))
INSERT [dbo].[tblFood] ([foodID], [foodName], [img], [amount], [price], [description], [typeID], [status], [createDate]) VALUES (N'qqqq', N'het ban roi', N'image/1.jpg', 2, 15000, N'asd', N'CD', 1, CAST(N'2021-01-20' AS Date))
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O1', N'a', 16000, 0, CAST(N'2021-01-10' AS Date), N'12 lê vân việt')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O10', N'a', 16000, 1, CAST(N'2021-01-11' AS Date), N'12 lê vân việt lalala')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O11', N'a', 35000, 1, CAST(N'2021-01-11' AS Date), N'12 lê vân việt')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O12', N'a', 177000, 1, CAST(N'2021-01-19' AS Date), N'12 lê vân việt la')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O13', N'101504280', 70000, 1, CAST(N'2021-01-19' AS Date), N'15 thu duc')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O14', N'101504280', 10000, 1, CAST(N'2021-01-19' AS Date), N'15 asd')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O15', N'a', 34000, 0, CAST(N'2021-01-20' AS Date), N'12 lê vân việt')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O16', N'a', 50000, 0, CAST(N'2021-01-20' AS Date), N'12 lê vân việt')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O17', N'a', 46000, 1, CAST(N'2021-01-20' AS Date), N'12 lê vân việt')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O18', N'101504280', 25000, 1, CAST(N'2021-02-24' AS Date), N'asdsad')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O19', N'101504280', 22000, 1, CAST(N'2021-02-24' AS Date), N'15 thu duc')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O2', N'b123', 15000, 0, CAST(N'2021-01-11' AS Date), N'12 lê vân việt')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O20', N'user', 37000, 1, CAST(N'2021-03-15' AS Date), N'13 asd')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O21', N'user', 80000, 1, CAST(N'2021-03-17' AS Date), N'13 asd')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O22', N'user', 24000, 1, CAST(N'2021-03-17' AS Date), N'13 asd')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O23', N'user', 165000, 1, CAST(N'2021-03-20' AS Date), N'13 asd')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O24', N'user', 201000, 1, CAST(N'2021-03-20' AS Date), N'13 asd')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O25', N'user', 110000, 1, CAST(N'2021-03-20' AS Date), N'13 asd')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O26', N'a', 96000, 1, CAST(N'2021-03-20' AS Date), N'12 lê vân việt')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O27', N'a', 34000, 1, CAST(N'2021-03-20' AS Date), N'12 lê vân việt')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O3', N'a', 59000, 0, CAST(N'2021-01-10' AS Date), N'12 lê vân việt')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O4', N'a', 54000, 0, CAST(N'2021-01-10' AS Date), N'12 lê vân việt')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O5', N'a', 104000, 1, CAST(N'2021-01-10' AS Date), N'12 lê vân việt')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O6', N'a', 56000, 1, CAST(N'2021-01-11' AS Date), N'12 lê vân việt')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O7', N'a', 580000, 1, CAST(N'2021-01-11' AS Date), N'12 lê vân việt')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O8', N'a', 15000, 1, CAST(N'2021-01-11' AS Date), N'12 lê vân việt FFF')
INSERT [dbo].[tblOrder] ([orderID], [userID], [totalPrice], [status], [orderDate], [addressDelivery]) VALUES (N'O9', N'a', 1034000, 1, CAST(N'2021-01-11' AS Date), N'12 lê vân việt')
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD1', N'O5', N'FC0002', 1, 12000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD10', N'O9', N'FC0009', 9, 16000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD11', N'O10', N'FC0009', 1, 16000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD12', N'O11', N'FC0003', 1, 35000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD13', N'O12', N'FC0002', 1, 12000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD14', N'O17', N'FC0011', 1, 5000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD15', N'O17', N'FC0013', 1, 25000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD16', N'O17', N'FC0047', 1, 16000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD17', N'O18', N'FC0001', 1, 10000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD18', N'O18', N'FC0025', 1, 15000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD19', N'O19', N'FC0002', 1, 12000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD2', N'O9', N'FC0001', 5, 10000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD20', N'O19', N'FC0001', 1, 10000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD21', N'O20', N'FC0002', 1, 12000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD22', N'O20', N'FC0001', 1, 10000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD23', N'O20', N'FC0025', 1, 15000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD24', N'O21', N'FC0012', 1, 65000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD25', N'O21', N'FC0025', 1, 15000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD26', N'O22', N'FC0002', 2, 12000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD27', N'O23', N'FC0032', 2, 15000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD28', N'O23', N'FC0025', 2, 15000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD29', N'O23', N'FC0027', 3, 35000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD3', N'O9', N'FC0001', 10, 10000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD30', N'O24', N'FC0022', 2, 48000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD31', N'O24', N'FC0027', 3, 35000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD32', N'O25', N'FC0011', 3, 5000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD33', N'O25', N'FC0013', 2, 25000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD34', N'O25', N'FC0014', 3, 15000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD35', N'O26', N'FC0002', 2, 12000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD36', N'O26', N'FC0001', 2, 10000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD37', N'O26', N'FC0028', 2, 26000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD38', N'O27', N'FC0002', 2, 12000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD39', N'O27', N'FC0001', 1, 10000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD4', N'O6', N'FC0002', 3, 12000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD5', N'O6', N'FC0001', 2, 10000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD6', N'O7', N'FC0001', 58, 10000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD7', N'O8', N'FC0004', 1, 15000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD8', N'O9', N'FC0002', 45, 12000, 1)
INSERT [dbo].[tblOrderDetail] ([detailID], [orderID], [foodID], [quantity], [price], [status]) VALUES (N'OD9', N'O9', N'FC0001', 35, 10000, 1)
INSERT [dbo].[tblRecordUpdate] ([recordID], [userID], [foodID], [dateUpdate]) VALUES (N'RU1', N'admin', N'FC0999', CAST(N'2019-01-06' AS Date))
INSERT [dbo].[tblRecordUpdate] ([recordID], [userID], [foodID], [dateUpdate]) VALUES (N'RU45', N'admin', N'FC0999', CAST(N'2021-01-18' AS Date))
INSERT [dbo].[tblRecordUpdate] ([recordID], [userID], [foodID], [dateUpdate]) VALUES (N'RU48', N'admin', N'FC0026', CAST(N'2021-01-18' AS Date))
INSERT [dbo].[tblRecordUpdate] ([recordID], [userID], [foodID], [dateUpdate]) VALUES (N'RU49', N'admin', N'FC0003', CAST(N'2020-01-04' AS Date))
INSERT [dbo].[tblRecordUpdate] ([recordID], [userID], [foodID], [dateUpdate]) VALUES (N'RU5', N'admin', N'FC0999', CAST(N'2019-01-06' AS Date))
INSERT [dbo].[tblRecordUpdate] ([recordID], [userID], [foodID], [dateUpdate]) VALUES (N'RU50', N'admin', N'FC0003', CAST(N'2020-01-04' AS Date))
INSERT [dbo].[tblRecordUpdate] ([recordID], [userID], [foodID], [dateUpdate]) VALUES (N'RU52', N'admin', N'FC0999', CAST(N'2021-01-20' AS Date))
INSERT [dbo].[tblRecordUpdate] ([recordID], [userID], [foodID], [dateUpdate]) VALUES (N'RU53', N'admin', N'FC0020', CAST(N'2021-01-20' AS Date))
INSERT [dbo].[tblRecordUpdate] ([recordID], [userID], [foodID], [dateUpdate]) VALUES (N'RU6', N'admin', N'FC0009', CAST(N'2019-11-30' AS Date))
INSERT [dbo].[tblRecordUpdate] ([recordID], [userID], [foodID], [dateUpdate]) VALUES (N'RU7', N'admin', N'FC0999', CAST(N'2019-01-06' AS Date))
INSERT [dbo].[tblRole] ([roleId], [roleName], [status]) VALUES (N'AD', N'administrator', 1)
INSERT [dbo].[tblRole] ([roleId], [roleName], [status]) VALUES (N'UG', N'user gmail', 1)
INSERT [dbo].[tblRole] ([roleId], [roleName], [status]) VALUES (N'US', N'user', 1)
INSERT [dbo].[tblUser] ([userId], [fullName], [password], [roleID], [status], [address]) VALUES (N'101504280', N'Dương Đỗ', N'', N'UG', 1, N'')
INSERT [dbo].[tblUser] ([userId], [fullName], [password], [roleID], [status], [address]) VALUES (N'a', N'No Name', N'1', N'US', 1, N'12 lê vân việt')
INSERT [dbo].[tblUser] ([userId], [fullName], [password], [roleID], [status], [address]) VALUES (N'admin', N'Quản lý', N'123456', N'AD', 1, NULL)
INSERT [dbo].[tblUser] ([userId], [fullName], [password], [roleID], [status], [address]) VALUES (N'b123', N'John', N'12', N'US', 0, N'sky 9')
INSERT [dbo].[tblUser] ([userId], [fullName], [password], [roleID], [status], [address]) VALUES (N'test', N'Teacher', N'123456', N'US', 1, N'địa chỉ')
INSERT [dbo].[tblUser] ([userId], [fullName], [password], [roleID], [status], [address]) VALUES (N'user', N'Nguyễn Văn A', N'asd', N'US', 1, N'13 asd')
ALTER TABLE [dbo].[tblFood]  WITH CHECK ADD FOREIGN KEY([typeID])
REFERENCES [dbo].[tblCategory] ([typeID])
GO
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD FOREIGN KEY([userID])
REFERENCES [dbo].[tblUser] ([userId])
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD FOREIGN KEY([foodID])
REFERENCES [dbo].[tblFood] ([foodID])
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD FOREIGN KEY([orderID])
REFERENCES [dbo].[tblOrder] ([orderID])
GO
ALTER TABLE [dbo].[tblRecordUpdate]  WITH CHECK ADD FOREIGN KEY([foodID])
REFERENCES [dbo].[tblFood] ([foodID])
GO
ALTER TABLE [dbo].[tblRecordUpdate]  WITH CHECK ADD FOREIGN KEY([userID])
REFERENCES [dbo].[tblUser] ([userId])
GO
ALTER TABLE [dbo].[tblUser]  WITH CHECK ADD FOREIGN KEY([roleID])
REFERENCES [dbo].[tblRole] ([roleId])
GO
