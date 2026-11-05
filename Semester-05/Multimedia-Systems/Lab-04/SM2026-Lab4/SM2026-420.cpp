#include "SM2026-420.h"

#include <SDL2/SDL.h>

#include "SM2026-HSL.h"
#include "SM2026-Lab4.h"
#include "SM2026-YCbCr.h"
#include "SM2026-YIQ.h"
#include "SM2026-YUV.h"

void subsample420YUV() {
  for (int yy = 0; yy < wysokosc / 2; yy += 2) {
    for (int xx = 0; xx < szerokosc / 2; xx += 2) {
      YUV yuv00 = getYUV(xx, yy);
      YUV yuv10 = getYUV(xx + 1, yy);
      YUV yuv01 = getYUV(xx, yy + 1);
      YUV yuv11 = getYUV(xx + 1, yy + 1);

      float uAvg = (yuv00.m_u + yuv10.m_u + yuv01.m_u + yuv11.m_u) / 4.0f;
      float vAvg = (yuv00.m_v + yuv10.m_v + yuv01.m_v + yuv11.m_v) / 4.0f;

      int xOut = xx + szerokosc / 2;
      int yOut = yy;

      setYUV(xOut, yOut, yuv00.m_y, uAvg, vAvg);
      setYUV(xOut + 1, yOut, yuv10.m_y, uAvg, vAvg);
      setYUV(xOut, yOut + 1, yuv01.m_y, uAvg, vAvg);
      setYUV(xOut + 1, yOut + 1, yuv11.m_y, uAvg, vAvg);
    }
  }
}

void subsample420YIQ() {
  for (int yy = 0; yy < wysokosc / 2; yy += 2) {
    for (int xx = 0; xx < szerokosc / 2; xx += 2) {
      YIQ yiq00 = getYIQ(xx, yy);
      YIQ yiq10 = getYIQ(xx + 1, yy);
      YIQ yiq01 = getYIQ(xx, yy + 1);
      YIQ yiq11 = getYIQ(xx + 1, yy + 1);

      float iAvg = (yiq00.m_i + yiq10.m_i + yiq01.m_i + yiq11.m_i) / 4.0f;
      float qAvg = (yiq00.m_q + yiq10.m_q + yiq01.m_q + yiq11.m_q) / 4.0f;

      int xOut = xx;
      int yOut = yy + wysokosc / 2;

      setYIQ(xOut, yOut, yiq00.m_y, iAvg, qAvg);
      setYIQ(xOut + 1, yOut, yiq10.m_y, iAvg, qAvg);
      setYIQ(xOut, yOut + 1, yiq01.m_y, iAvg, qAvg);
      setYIQ(xOut + 1, yOut + 1, yiq11.m_y, iAvg, qAvg);
    }
  }
}

void subsample420YCbCr() {
  for (int yy = 0; yy < wysokosc / 2; yy += 2) {
    for (int xx = 0; xx < szerokosc / 2; xx += 2) {
      YCbCr ycbcr00 = getYCbCr(xx, yy);
      YCbCr ycbcr10 = getYCbCr(xx + 1, yy);
      YCbCr ycbcr01 = getYCbCr(xx, yy + 1);
      YCbCr ycbcr11 = getYCbCr(xx + 1, yy + 1);

      float cbAvg =
          (ycbcr00.m_cb + ycbcr10.m_cb + ycbcr01.m_cb + ycbcr11.m_cb) / 4.0f;
      float crAvg =
          (ycbcr00.m_cr + ycbcr10.m_cr + ycbcr01.m_cr + ycbcr11.m_cr) / 4.0f;

      int xOut = xx + szerokosc / 2;
      int yOut = yy + wysokosc / 2;

      setYCbCr(xOut, yOut, ycbcr00.m_y, cbAvg, crAvg);
      setYCbCr(xOut + 1, yOut, ycbcr10.m_y, cbAvg, crAvg);
      setYCbCr(xOut, yOut + 1, ycbcr01.m_y, cbAvg, crAvg);
      setYCbCr(xOut + 1, yOut + 1, ycbcr11.m_y, cbAvg, crAvg);
    }
  }
}

void subsample420R() {
  for (int yy = 0; yy < wysokosc / 2; yy += 2) {
    for (int xx = 0; xx < szerokosc / 2; xx += 2) {
      SDL_Color color00 = getPixel(xx, yy);
      SDL_Color color10 = getPixel(xx + 1, yy);
      SDL_Color color01 = getPixel(xx, yy + 1);
      SDL_Color color11 = getPixel(xx + 1, yy + 1);

      float rAvg = (color00.r + color10.r + color01.r + color11.r) / 4.0f;
      Uint8 rNorm = normalizeUint8(rAvg);

      int xOut = xx + szerokosc / 2;
      int yOut = yy;

      setPixel(xOut, yOut, rNorm, color00.g, color00.b);
      setPixel(xOut + 1, yOut, rNorm, color10.g, color10.b);
      setPixel(xOut, yOut + 1, rNorm, color01.g, color01.b);
      setPixel(xOut + 1, yOut + 1, rNorm, color11.g, color11.b);
    }
  }
}

void subsample420G() {
  for (int yy = 0; yy < wysokosc / 2; yy += 2) {
    for (int xx = 0; xx < szerokosc / 2; xx += 2) {
      SDL_Color color00 = getPixel(xx, yy);
      SDL_Color color10 = getPixel(xx + 1, yy);
      SDL_Color color01 = getPixel(xx, yy + 1);
      SDL_Color color11 = getPixel(xx + 1, yy + 1);

      float gAvg = (color00.g + color10.g + color01.g + color11.g) / 4.0f;
      Uint8 gNorm = normalizeUint8(gAvg);

      int xOut = xx;
      int yOut = yy + wysokosc / 2;

      setPixel(xOut, yOut, color00.r, gNorm, color00.b);
      setPixel(xOut + 1, yOut, color10.r, gNorm, color10.b);
      setPixel(xOut, yOut + 1, color01.r, gNorm, color01.b);
      setPixel(xOut + 1, yOut + 1, color11.r, gNorm, color11.b);
    }
  }
}

void subsample420B() {
  for (int yy = 0; yy < wysokosc / 2; yy += 2) {
    for (int xx = 0; xx < szerokosc / 2; xx += 2) {
      SDL_Color color00 = getPixel(xx, yy);
      SDL_Color color10 = getPixel(xx + 1, yy);
      SDL_Color color01 = getPixel(xx, yy + 1);
      SDL_Color color11 = getPixel(xx + 1, yy + 1);

      float bAvg = (color00.b + color10.b + color01.b + color11.b) / 4.0f;
      Uint8 bNorm = normalizeUint8(bAvg);

      int xOut = xx + szerokosc / 2;
      int yOut = yy + wysokosc / 2;

      setPixel(xOut, yOut, color00.r, color00.g, bNorm);
      setPixel(xOut + 1, yOut, color10.r, color10.g, bNorm);
      setPixel(xOut, yOut + 1, color01.r, color01.g, bNorm);
      setPixel(xOut + 1, yOut + 1, color11.r, color11.g, bNorm);
    }
  }
}

void subsample420H() {
  for (int yy = 0; yy < wysokosc / 2; yy += 2) {
    for (int xx = 0; xx < szerokosc / 2; xx += 2) {
      HSL hsl00 = getHSL(xx, yy);
      HSL hsl10 = getHSL(xx + 1, yy);
      HSL hsl01 = getHSL(xx, yy + 1);
      HSL hsl11 = getHSL(xx + 1, yy + 1);

      float hAvg = (hsl00.m_h + hsl10.m_h + hsl01.m_h + hsl11.m_h) / 4.0f;

      int xOut = xx + szerokosc / 2;
      int yOut = yy;

      setHSL(xOut, yOut, hAvg, hsl00.m_s, hsl00.m_l);
      setHSL(xOut + 1, yOut, hAvg, hsl10.m_s, hsl10.m_l);
      setHSL(xOut, yOut + 1, hAvg, hsl01.m_s, hsl01.m_l);
      setHSL(xOut + 1, yOut + 1, hAvg, hsl11.m_s, hsl11.m_l);
    }
  }
}

void subsample420S() {
  for (int yy = 0; yy < wysokosc / 2; yy += 2) {
    for (int xx = 0; xx < szerokosc / 2; xx += 2) {
      HSL hsl00 = getHSL(xx, yy);
      HSL hsl10 = getHSL(xx + 1, yy);
      HSL hsl01 = getHSL(xx, yy + 1);
      HSL hsl11 = getHSL(xx + 1, yy + 1);

      float sAvg = (hsl00.m_s + hsl10.m_s + hsl01.m_s + hsl11.m_s) / 4.0f;

      int xOut = xx;
      int yOut = yy + wysokosc / 2;

      setHSL(xOut, yOut, hsl00.m_h, sAvg, hsl00.m_l);
      setHSL(xOut + 1, yOut, hsl10.m_h, sAvg, hsl10.m_l);
      setHSL(xOut, yOut + 1, hsl01.m_h, sAvg, hsl01.m_l);
      setHSL(xOut + 1, yOut + 1, hsl11.m_h, sAvg, hsl11.m_l);
    }
  }
}

void subsample420L() {
  for (int yy = 0; yy < wysokosc / 2; yy += 2) {
    for (int xx = 0; xx < szerokosc / 2; xx += 2) {
      HSL hsl00 = getHSL(xx, yy);
      HSL hsl10 = getHSL(xx + 1, yy);
      HSL hsl01 = getHSL(xx, yy + 1);
      HSL hsl11 = getHSL(xx + 1, yy + 1);

      float lAvg = (hsl00.m_l + hsl10.m_l + hsl01.m_l + hsl11.m_l) / 4.0f;

      int xOut = xx + szerokosc / 2;
      int yOut = yy + wysokosc / 2;

      setHSL(xOut, yOut, hsl00.m_h, hsl00.m_s, lAvg);
      setHSL(xOut + 1, yOut, hsl10.m_h, hsl10.m_s, lAvg);
      setHSL(xOut, yOut + 1, hsl01.m_h, hsl01.m_s, lAvg);
      setHSL(xOut + 1, yOut + 1, hsl11.m_h, hsl11.m_s, lAvg);
    }
  }
}