#ifndef SM2026_YCBCR_H_
#define SM2026_YCBCR_H_

struct YCbCr {
  float m_y;
  float m_cb;
  float m_cr;

  YCbCr(float y = 0.0f, float cb = 0.0f, float cr = 0.0f)
      : m_y(y), m_cb(cb), m_cr(cr) {}
};

void setYCbCr(int xx, int yy, float y, float cb, float cr);
YCbCr getYCbCr(int xx, int yy);

#endif  // SM2026_YCBCR_H_