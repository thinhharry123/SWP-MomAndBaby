USE [master]
GO
/****** Object:  Database [database_mom_baby]    Script Date: 24/06/2024 3:42:19 CH ******/
CREATE DATABASE [database_mom_baby]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'database_mom_baby', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER01\MSSQL\DATA\database_mom_baby.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'database_mom_baby_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER01\MSSQL\DATA\database_mom_baby_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [database_mom_baby] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [database_mom_baby].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [database_mom_baby] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [database_mom_baby] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [database_mom_baby] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [database_mom_baby] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [database_mom_baby] SET ARITHABORT OFF 
GO
ALTER DATABASE [database_mom_baby] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [database_mom_baby] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [database_mom_baby] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [database_mom_baby] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [database_mom_baby] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [database_mom_baby] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [database_mom_baby] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [database_mom_baby] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [database_mom_baby] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [database_mom_baby] SET  ENABLE_BROKER 
GO
ALTER DATABASE [database_mom_baby] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [database_mom_baby] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [database_mom_baby] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [database_mom_baby] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [database_mom_baby] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [database_mom_baby] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [database_mom_baby] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [database_mom_baby] SET RECOVERY FULL 
GO
ALTER DATABASE [database_mom_baby] SET  MULTI_USER 
GO
ALTER DATABASE [database_mom_baby] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [database_mom_baby] SET DB_CHAINING OFF 
GO
ALTER DATABASE [database_mom_baby] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [database_mom_baby] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [database_mom_baby] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [database_mom_baby] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'database_mom_baby', N'ON'
GO
ALTER DATABASE [database_mom_baby] SET QUERY_STORE = ON
GO
ALTER DATABASE [database_mom_baby] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [database_mom_baby]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 24/06/2024 3:42:20 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](100) NOT NULL,
	[password] [varchar](32) NOT NULL,
	[email] [varchar](1000) NOT NULL,
	[phone] [varchar](1000) NOT NULL,
	[status] [tinyint] NOT NULL,
	[fullname] [nvarchar](200) NULL,
	[date] [datetime] NOT NULL,
	[role] [int] NOT NULL,
	[avatar] [nchar](1000) NULL,
	[balance] [float] NULL,
 CONSTRAINT [PK__User__3214EC271AACD699] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Banner]    Script Date: 24/06/2024 3:42:20 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Banner](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[img] [varchar](500) NOT NULL,
	[name] [nvarchar](500) NOT NULL,
	[status] [tinyint] NOT NULL,
	[datePost] [datetime] NOT NULL,
	[dateUpdate] [datetime] NULL,
 CONSTRAINT [PK_Banner] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Bill]    Script Date: 24/06/2024 3:42:20 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bill](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[accountID] [int] NULL,
	[email] [varchar](1000) NOT NULL,
	[customerName] [nvarchar](100) NOT NULL,
	[phone] [varchar](100) NOT NULL,
	[address] [nvarchar](1000) NOT NULL,
	[detailAddress] [nvarchar](1000) NULL,
	[total] [float] NOT NULL,
	[status] [tinyint] NOT NULL,
	[isGetPoint] [tinyint] NOT NULL,
	[isUsedPoint] [float] NOT NULL,
	[payment] [tinyint] NOT NULL,
	[dateOrder] [datetime] NOT NULL,
	[dateUpdate] [datetime] NULL,
	[transactionCode] [nvarchar](50) NULL,
	[voucherID] [int] NULL,
 CONSTRAINT [PK__Bill__3214EC273703A317] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BillDetail]    Script Date: 24/06/2024 3:42:20 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BillDetail](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[billID] [int] NOT NULL,
	[imgProduct] [varchar](2000) NOT NULL,
	[numberOfProduct] [int] NOT NULL,
	[priceProduct] [float] NOT NULL,
	[nameProduct] [nvarchar](1000) NOT NULL,
	[productID] [int] NOT NULL,
 CONSTRAINT [PK__BillDeta__3214EC272552895B] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Blog]    Script Date: 24/06/2024 3:42:20 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Blog](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](500) NOT NULL,
	[shortDesc] [nvarchar](1000) NULL,
	[description] [nvarchar](max) NOT NULL,
	[image] [nvarchar](200) NOT NULL,
	[datePost] [datetime] NOT NULL,
	[dateUpdate] [datetime] NULL,
	[status] [int] NOT NULL,
	[view] [bigint] NOT NULL,
	[categoryID] [int] NOT NULL,
 CONSTRAINT [PK_Blog] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Brand]    Script Date: 24/06/2024 3:42:20 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Brand](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](500) NOT NULL,
	[img] [varchar](500) NOT NULL,
	[datePost] [datetime] NOT NULL,
	[dateUpdate] [datetime] NULL,
	[status] [tinyint] NOT NULL,
 CONSTRAINT [PK_Brand] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cart]    Script Date: 24/06/2024 3:42:20 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cart](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[accountID] [int] NOT NULL,
	[totalPrice] [float] NOT NULL,
	[quantity] [int] NOT NULL,
	[productID] [int] NOT NULL,
 CONSTRAINT [PK_Cart] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 24/06/2024 3:42:20 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](1000) NOT NULL,
	[datePost] [datetime] NOT NULL,
	[dateUpdate] [datetime] NULL,
	[status] [tinyint] NOT NULL,
 CONSTRAINT [PK__Category__3214EC2763872BFD] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[district]    Script Date: 24/06/2024 3:42:20 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[district](
	[district_id] [int] NOT NULL,
	[province_id] [int] NOT NULL,
	[name] [nvarchar](150) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[district_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Feedback]    Script Date: 24/06/2024 3:42:20 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Feedback](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[userID] [int] NOT NULL,
	[productID] [int] NULL,
	[feedback] [nvarchar](1000) NOT NULL,
	[star] [int] NOT NULL,
	[status] [tinyint] NOT NULL,
	[datePost] [datetime] NOT NULL,
	[dateUpdate] [datetime] NULL,
	[billID] [int] NOT NULL,
 CONSTRAINT [PK__Comment__3214EC27AE11BE00] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ImgDescription]    Script Date: 24/06/2024 3:42:20 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ImgDescription](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[imgUrl] [varchar](1000) NOT NULL,
	[productID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[preOrder]    Script Date: 24/06/2024 3:42:20 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[preOrder](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[productID] [int] NOT NULL,
	[accountID] [int] NOT NULL,
	[status] [tinyint] NOT NULL,
 CONSTRAINT [PK_preOrder] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Producer]    Script Date: 24/06/2024 3:42:20 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Producer](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](1000) NOT NULL,
	[datePost] [datetime] NOT NULL,
	[dateUpdate] [datetime] NULL,
	[status] [tinyint] NOT NULL,
 CONSTRAINT [PK__Producer__3214EC27087E67DC] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 24/06/2024 3:42:20 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](1000) NOT NULL,
	[oldPrice] [float] NOT NULL,
	[newPrice] [float] NOT NULL,
	[description] [nvarchar](max) NOT NULL,
	[datePost] [datetime] NOT NULL,
	[dateUpdate] [datetime] NULL,
	[mainImg] [varchar](2000) NOT NULL,
	[status] [tinyint] NOT NULL,
	[quantity] [int] NOT NULL,
	[sold] [int] NULL,
	[model] [varchar](100) NOT NULL,
	[priority] [tinyint] NOT NULL,
	[categoryID] [int] NOT NULL,
	[producerID] [int] NOT NULL,
	[brandID] [int] NOT NULL,
 CONSTRAINT [PK__Product__3214EC27D5ABB81D] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[province]    Script Date: 24/06/2024 3:42:20 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[province](
	[province_id] [int] NOT NULL,
	[name] [nvarchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[province_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 24/06/2024 3:42:20 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](100) NOT NULL,
	[type] [tinyint] NOT NULL,
	[status] [tinyint] NOT NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[used]    Script Date: 24/06/2024 3:42:20 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[used](
	[accountID] [int] NOT NULL,
	[voucherID] [int] NOT NULL,
	[dateUse] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[accountID] ASC,
	[voucherID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[voucher]    Script Date: 24/06/2024 3:42:20 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[voucher](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](500) NOT NULL,
	[code] [nvarchar](50) NULL,
	[value] [float] NOT NULL,
	[start] [datetime] NOT NULL,
	[end] [datetime] NOT NULL,
	[status] [tinyint] NOT NULL,
	[limit] [float] NULL,
	[used] [varchar](max) NULL,
 CONSTRAINT [PK_voucher] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[wards]    Script Date: 24/06/2024 3:42:20 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[wards](
	[wards_id] [int] NOT NULL,
	[district_id] [int] NOT NULL,
	[name] [nvarchar](200) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[wards_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Account] ADD  CONSTRAINT [DF__User__status__35BCFE0A]  DEFAULT ((1)) FOR [status]
GO
ALTER TABLE [dbo].[Account] ADD  CONSTRAINT [DF_Account_balance]  DEFAULT ((0)) FOR [balance]
GO
ALTER TABLE [dbo].[Bill] ADD  CONSTRAINT [DF_Bill_customerID]  DEFAULT (NULL) FOR [accountID]
GO
ALTER TABLE [dbo].[Bill] ADD  CONSTRAINT [DF__Bill__status__398D8EEE]  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[Bill] ADD  CONSTRAINT [DF_Bill_isGetPoint]  DEFAULT ((0)) FOR [isGetPoint]
GO
ALTER TABLE [dbo].[Bill] ADD  CONSTRAINT [DF_Bill_isUsedPoint]  DEFAULT ((0)) FOR [isUsedPoint]
GO
ALTER TABLE [dbo].[Category] ADD  CONSTRAINT [DF__Category__status__24927208]  DEFAULT ((1)) FOR [status]
GO
ALTER TABLE [dbo].[Feedback] ADD  CONSTRAINT [DF__Comment__status__412EB0B6]  DEFAULT ((1)) FOR [status]
GO
ALTER TABLE [dbo].[Producer] ADD  CONSTRAINT [DF__Producer__status__276EDEB3]  DEFAULT ((1)) FOR [status]
GO
ALTER TABLE [dbo].[Product] ADD  CONSTRAINT [DF__Product__status__2A4B4B5E]  DEFAULT ((1)) FOR [status]
GO
ALTER TABLE [dbo].[Product] ADD  CONSTRAINT [DF_Product_sold]  DEFAULT ((0)) FOR [sold]
GO
ALTER TABLE [dbo].[Product] ADD  CONSTRAINT [DF__Product__priorit__2B3F6F97]  DEFAULT ((0)) FOR [priority]
GO
ALTER TABLE [dbo].[Bill]  WITH CHECK ADD  CONSTRAINT [FK__Bill__customerID__38996AB5] FOREIGN KEY([accountID])
REFERENCES [dbo].[Account] ([ID])
GO
ALTER TABLE [dbo].[Bill] CHECK CONSTRAINT [FK__Bill__customerID__38996AB5]
GO
ALTER TABLE [dbo].[Bill]  WITH CHECK ADD  CONSTRAINT [FK_Bill_voucher] FOREIGN KEY([voucherID])
REFERENCES [dbo].[voucher] ([ID])
GO
ALTER TABLE [dbo].[Bill] CHECK CONSTRAINT [FK_Bill_voucher]
GO
ALTER TABLE [dbo].[BillDetail]  WITH CHECK ADD  CONSTRAINT [FK__BillDetai__billI__3C69FB99] FOREIGN KEY([billID])
REFERENCES [dbo].[Bill] ([ID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[BillDetail] CHECK CONSTRAINT [FK__BillDetai__billI__3C69FB99]
GO
ALTER TABLE [dbo].[Blog]  WITH CHECK ADD  CONSTRAINT [FK_Blog_Category] FOREIGN KEY([categoryID])
REFERENCES [dbo].[Category] ([ID])
GO
ALTER TABLE [dbo].[Blog] CHECK CONSTRAINT [FK_Blog_Category]
GO
ALTER TABLE [dbo].[Cart]  WITH CHECK ADD  CONSTRAINT [FK_Cart_Account] FOREIGN KEY([accountID])
REFERENCES [dbo].[Account] ([ID])
GO
ALTER TABLE [dbo].[Cart] CHECK CONSTRAINT [FK_Cart_Account]
GO
ALTER TABLE [dbo].[Cart]  WITH CHECK ADD  CONSTRAINT [FK_Cart_Product] FOREIGN KEY([productID])
REFERENCES [dbo].[Product] ([ID])
GO
ALTER TABLE [dbo].[Cart] CHECK CONSTRAINT [FK_Cart_Product]
GO
ALTER TABLE [dbo].[district]  WITH CHECK ADD  CONSTRAINT [FK_district_province] FOREIGN KEY([province_id])
REFERENCES [dbo].[province] ([province_id])
GO
ALTER TABLE [dbo].[district] CHECK CONSTRAINT [FK_district_province]
GO
ALTER TABLE [dbo].[Feedback]  WITH CHECK ADD  CONSTRAINT [FK__Comment__userID__3F466844] FOREIGN KEY([userID])
REFERENCES [dbo].[Account] ([ID])
GO
ALTER TABLE [dbo].[Feedback] CHECK CONSTRAINT [FK__Comment__userID__3F466844]
GO
ALTER TABLE [dbo].[ImgDescription]  WITH CHECK ADD  CONSTRAINT [FK__ImgDescri__produ__300424B4] FOREIGN KEY([productID])
REFERENCES [dbo].[Product] ([ID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ImgDescription] CHECK CONSTRAINT [FK__ImgDescri__produ__300424B4]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK__Product__categor__2C3393D0] FOREIGN KEY([categoryID])
REFERENCES [dbo].[Category] ([ID])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK__Product__categor__2C3393D0]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK__Product__produce__2D27B809] FOREIGN KEY([producerID])
REFERENCES [dbo].[Producer] ([ID])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK__Product__produce__2D27B809]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Brand] FOREIGN KEY([brandID])
REFERENCES [dbo].[Brand] ([ID])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Brand]
GO
ALTER TABLE [dbo].[used]  WITH CHECK ADD  CONSTRAINT [FK_used_Account] FOREIGN KEY([accountID])
REFERENCES [dbo].[Account] ([ID])
GO
ALTER TABLE [dbo].[used] CHECK CONSTRAINT [FK_used_Account]
GO
ALTER TABLE [dbo].[used]  WITH CHECK ADD  CONSTRAINT [FK_used_voucher] FOREIGN KEY([voucherID])
REFERENCES [dbo].[voucher] ([ID])
GO
ALTER TABLE [dbo].[used] CHECK CONSTRAINT [FK_used_voucher]
GO
ALTER TABLE [dbo].[wards]  WITH CHECK ADD  CONSTRAINT [FK_wards_district] FOREIGN KEY([district_id])
REFERENCES [dbo].[district] ([district_id])
GO
ALTER TABLE [dbo].[wards] CHECK CONSTRAINT [FK_wards_district]
GO
USE [master]
GO
ALTER DATABASE [database_mom_baby] SET  READ_WRITE 
GO
