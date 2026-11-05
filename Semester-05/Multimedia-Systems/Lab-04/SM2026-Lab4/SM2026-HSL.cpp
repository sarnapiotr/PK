#include "SM2026-HSL.h"

#include <SDL2/SDL.h>

#include <algorithm>

#include "SM2026-Lab4.h"

static float normalizeCol01(float val);
static float testCol01(float col01, float temp1, float temp2);

void setHSL(int xx, int yy, float h, float s, float l) {
  if (s == 0.0f) {
    Uint8 gray = normalizeUint8(l * 255.0f);
    setPixel(xx, yy, gray, gray, gray);
    return;
  }

  float temp1 = 0.0f;
  if (l < 0.5f) {
    temp1 = l * (1.0f + s);
  } else {
    temp1 = l + s - l * s;
  }

  float temp2 = 2.0f * l - temp1;

  float h01 = h / 360.0f;

  float r01 = normalizeCol01(h01 + (1.0f / 3.0f));
  float g01 = normalizeCol01(h01);
  float b01 = normalizeCol01(h01 - (1.0f / 3.0f));

  r01 = testCol01(r01, temp1, temp2);
  g01 = testCol01(g01, temp1, temp2);
  b01 = testCol01(b01, temp1, temp2);

  Uint8 rNorm = normalizeUint8(r01 * 255.0f);
  Uint8 gNorm = normalizeUint8(g01 * 255.0f);
  Uint8 bNorm = normalizeUint8(b01 * 255.0f);

  setPixel(xx, yy, rNorm, gNorm, bNorm);
}

HSL getHSL(int xx, int yy) {
  SDL_Color color = getPixel(xx, yy);

  Uint8 r = color.r;
  Uint8 g = color.g;
  Uint8 b = color.b;

  float r01 = r / 255.0f;
  float g01 = g / 255.0f;
  float b01 = b / 255.0f;

  float minVal = std::min({r01, g01, b01});
  float maxVal = std::max({r01, g01, b01});

  float l = (minVal + maxVal) / 2.0f;
  float s = 0.0f;
  float h = 0.0f;

  if (minVal == maxVal) {
    return HSL(h, s, l);
  }

  if (l <= 0.5f) {
    s = (maxVal - minVal) / (maxVal + minVal);
  } else {
    s = (maxVal - minVal) / (2.0f - maxVal - minVal);
  }

  if (r01 == maxVal) {
    h = (g01 - b01) / (maxVal - minVal);
  } else if (g01 == maxVal) {
    h = 2.0f + (b01 - r01) / (maxVal - minVal);
  } else {
    h = 4.0f + (r01 - g01) / (maxVal - minVal);
  }

  h *= 60.0f;
  if (h < 0.0f) h += 360.0f;

  return HSL(h, s, l);
}

static float normalizeCol01(float val) {
  if (val < 0.0f) {
    val += 1.0f;
  } else if (val > 1.0f) {
    val -= 1.0f;
  }
  return val;
}

static float testCol01(float col01, float temp1, float temp2) {
  if (6.0f * col01 < 1.0f) {
    return temp2 + (temp1 - temp2) * 6.0f * col01;
  }
  if (2.0f * col01 < 1.0f) {
    return temp1;
  }
  if (3.0f * col01 < 2.0f) {
    return temp2 + (temp1 - temp2) * ((2.0f / 3.0f) - col01) * 6.0f;
  }
  return temp2;
}